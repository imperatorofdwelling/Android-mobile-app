package com.imperatorofdwelling.android.presentation.ui.edit_profile

import com.imperatorofdwelling.android.presentation.ui.common.BaseViewModel
import com.imperatorofdwelling.android.presentation.ui.utils.Validator
import kotlinx.coroutines.flow.update

class EditProfileViewModel : BaseViewModel<EditProfileViewModel.State>(State()) {
    fun onNameChange(name: String) {
        _state.update { it.copy(name = name) }
        _state.update { it.copy(nameError = !Validator.isValidUserName(name)) }
        _state.update { it.copy(lengthNameError = !Validator.isValidLengthUserName(name)) }
        //clearServerError()
    }


    fun onEmailChange(email: String) {
        _state.update { it.copy(email = email) }
        _state.update { it.copy(emailError = !Validator.isValidEmail(email.trim())) }
        //clearServerError()
    }

    fun onPhoneChange(phone: String) {
        _state.update { it.copy(phone = phone) }
        _state.update { it.copy(phoneError = !Validator.isValidPhone(phone.trim())) }
    }

    fun onDateChange(newDate: String) {
//        val cleanedInput = newDate.filter { it.isDigit() }
//
//        val formattedDate = when (cleanedInput.length) {
//            in 0..2 -> cleanedInput
//            in 3..4 -> "${cleanedInput.take(2)}/${cleanedInput.drop(2)}"
//            else -> {
//                val day = cleanedInput.take(2)
//                val month = cleanedInput.drop(2).take(2)
//                val year = cleanedInput.drop(4).take(4)
//                "$day/$month/$year"
//            }
//        }

        _state.update { it.copy(date = newDate) }

        _state.update { it.copy(dateError = !Validator.isValidDate(newDate.trim())) }
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
        val place: String = ""
    )
}