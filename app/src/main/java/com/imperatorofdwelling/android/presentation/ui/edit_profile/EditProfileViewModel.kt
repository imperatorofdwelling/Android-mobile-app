package com.imperatorofdwelling.android.presentation.ui.edit_profile

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.imperatorofdwelling.android.domain.NetworkResult
import com.imperatorofdwelling.android.domain.user.usecases.GetUserDataUseCase
import com.imperatorofdwelling.android.presentation.ui.common.BaseViewModel
import com.imperatorofdwelling.android.presentation.ui.utils.LCE
import com.imperatorofdwelling.android.presentation.ui.utils.Validator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditProfileViewModel(
    private val getUserDataUseCase: GetUserDataUseCase
) : BaseViewModel<EditProfileViewModel.State>(State()) {

    init{
        initUserData()
    }

    private fun initUserData(){
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                _lce.value = LCE.Loading
                when(val result = getUserDataUseCase()){
                    is NetworkResult.Success -> {
                        withContext(Dispatchers.Main){
                            _state.update {
                                it.copy(
                                    name = result.value.name,
                                    email = result.value.email,
                                    phone = result.value.phone,
                                    date = result.value.birthDate?.time ?: "",
                                    place = result.value.city,
                                    isMale = result.value.gender == "Male",
                                    isFemale = result.value.gender == "Female"
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


    fun onEmailChange(email: String) {
        _state.update { it.copy(email = email) }
        _state.update { it.copy(emailError = !Validator.isValidEmail(email.trim())) }
    }

    fun onPhoneChange(phone: String) {
        _state.update { it.copy(phone = phone) }
        _state.update { it.copy(phoneError = !Validator.isValidPhone(phone.trim())) }
    }

    fun onDateChange(newDate: String) {
        _state.update { it.copy(date = newDate) }
        _state.update { it.copy(dateError = !Validator.isValidDate(newDate.trim())) }
    }

    fun onMaleSelected(boolean: Boolean){
        _state.update { it.copy(isMale = boolean) }
        _state.update { it.copy(isFemale = !boolean) }
    }

    fun onFemaleSelected(boolean: Boolean){
        _state.update { it.copy(isMale = !boolean) }
        _state.update { it.copy(isFemale = boolean) }
    }

    data class State(
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
        val isFemale: Boolean = false
    )
}