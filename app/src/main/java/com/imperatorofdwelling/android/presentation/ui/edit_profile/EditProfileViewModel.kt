package com.imperatorofdwelling.android.presentation.ui.edit_profile

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.imperatorofdwelling.android.domain.NetworkResult
import com.imperatorofdwelling.android.domain.user.entities.BirthDateDomain
import com.imperatorofdwelling.android.domain.user.entities.UserDomain
import com.imperatorofdwelling.android.domain.user.usecases.EditUserDataUseCase
import com.imperatorofdwelling.android.domain.user.usecases.GetUserDataUseCase
import com.imperatorofdwelling.android.presentation.ui.common.BaseViewModel
import com.imperatorofdwelling.android.presentation.ui.utils.LCE
import com.imperatorofdwelling.android.presentation.ui.utils.Validator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar

class EditProfileViewModel(
    private val getUserDataUseCase: GetUserDataUseCase,
    private val editUserDataUseCase: EditUserDataUseCase
) : BaseViewModel<EditProfileViewModel.State>(State()) {

    init {
        initUserData()
    }

    private fun initUserData() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                _lce.value = LCE.Loading
                when (val result = getUserDataUseCase()) {
                    is NetworkResult.Success -> {
                        withContext(Dispatchers.Main) {
                            _state.update {
                                it.copy(
                                    id = result.value.id,
                                    name = result.value.name,
                                    email = result.value.email,
                                    phone = result.value.phone ?: "",
                                    date = result.value.birthDate?.time ?: "",
                                    place = result.value.city ?: "",
                                    isMale = result.value.gender == "Male",
                                    isFemale = result.value.gender == "Female",
                                    createdAt = result.value.createdAt ?: "",
                                    updatedAt = result.value.updatedAt ?: ""
                                )
                            }
                        }
                        _lce.value = LCE.Idle
                    }

                    is NetworkResult.Error -> {
                        throw RuntimeException("GetUserDataException ${result.errorMessage}")
                    }
                }
            }.onFailure { e -> Log.e("ServerError", e.message.toString()) }
        }
    }

    fun onNameChange(name: String) {
        _state.update { it.copy(name = name) }
        _state.update { it.copy(nameError = !Validator.isValidUserName(name)) }
        _state.update { it.copy(lengthNameError = !Validator.isValidLengthUserName(name)) }
    }



    fun onDateChange(newDate: String) {
        _state.update { it.copy(date = newDate) }
        _state.update { it.copy(dateError = !Validator.isValidDate(newDate.trim())) }
    }

    fun onMaleSelected(boolean: Boolean) {
        _state.update { it.copy(isMale = boolean) }
        _state.update { it.copy(isFemale = !boolean) }
    }

    fun onFemaleSelected(boolean: Boolean) {
        _state.update { it.copy(isMale = !boolean) }
        _state.update { it.copy(isFemale = boolean) }
    }

    fun onEditClick() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                val birthDateString = if (_state.value.date.length == 10) {
                    _state.value.date
                } else {
                    null
                }
                val birthDate =
                    BirthDateDomain(time = birthDateString, valid = birthDateString != null)
                val updatedAtYear = Calendar.getInstance().get(Calendar.YEAR)
                val updatedAtMonth = Calendar.getInstance().get(Calendar.MONTH)
                val updatedAtDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
                val updatedAt = "$updatedAtYear-$updatedAtMonth-$updatedAtDay"
                val newData = UserDomain(
                    id = _state.value.id,
                    name = _state.value.name,
                    email = _state.value.email,
                    phone = _state.value.phone,
                    birthDate = birthDate,
                    gender = if (_state.value.isMale) {
                        "Male"
                    } else if (_state.value.isFemale) {
                        "Female"
                    } else {
                        null
                    },
                    city = _state.value.place,
                    createdAt = _state.value.createdAt,
                    updatedAt = if (_state.value.updatedAt == "") updatedAt else _state.value.updatedAt
                )
                when (val result = editUserDataUseCase(newData)) {
                    is NetworkResult.Success -> {
                        withContext(Dispatchers.Main) {
                            _state.update {
                                it.copy(
                                    id = result.value.id,
                                    name = result.value.name,
                                    email = result.value.email,
                                    phone = result.value.phone ?: "",
                                    date = result.value.birthDate?.time ?: "",
                                    place = result.value.city ?: "",
                                    isMale = result.value.gender == "Male",
                                    isFemale = result.value.gender == "Female",
                                    createdAt = result.value.createdAt ?: "",
                                    updatedAt = result.value.updatedAt ?: ""
                                )
                            }
                        }
                    }

                    is NetworkResult.Error -> {
                        throw RuntimeException("EditUserDataException ${result.errorMessage}")
                    }
                }
            }
        }
    }

    data class State(
        val id: String = "",
        val name: String = "",
        val email: String = "",
        val nameError: Boolean = false,
        val emailError: Boolean = false,
        val lengthNameError: Boolean = false,
        val phone: String = "",
        val phoneError: Boolean = false,
        val date: String = "",
        val dateError: Boolean = false,
        val place: String = "",
        val isMale: Boolean = false,
        val isFemale: Boolean = false,
        val createdAt: String = "",
        val updatedAt: String = ""
    )
}