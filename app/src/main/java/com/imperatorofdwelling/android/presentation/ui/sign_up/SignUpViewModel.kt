package com.imperatorofdwelling.android.presentation.ui.sign_up

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.imperatorofdwelling.android.domain.usecases.SignUpUseCase
import com.imperatorofdwelling.android.presentation.ui.utils.Validator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    companion object {
        private const val MINIMUM_LENGTH_USER_NAME = 3
        private const val MINIMUM_LENGTH_PASSWORD = 8
    }


    private val _errorSizeNameInput = MutableLiveData<Boolean>()
    val errorSizeNameInput: LiveData<Boolean>
        get() = _errorSizeNameInput

    private val _errorIncorrectNameInput = MutableLiveData<Boolean>()
    val errorIncorrectNameInput: LiveData<Boolean>
        get() = _errorIncorrectNameInput


    private val _errorEmailInput = MutableLiveData<Boolean>()
    val errorEmailInput: LiveData<Boolean>
        get() = _errorEmailInput

    private val _errorPasswordInput = MutableLiveData<Boolean>()
    val errorPasswordInput: LiveData<Boolean>
        get() = _errorPasswordInput

    private val _errorPasswordsEqualInput = MutableLiveData<Boolean>()
    val errorPasswordEqualsInput: LiveData<Boolean>
        get() = _errorPasswordsEqualInput

    private val _errorConfirmPolicy = MutableLiveData<Boolean>()
    val errorConfirmPolicy: LiveData<Boolean>
        get() = _errorConfirmPolicy

    fun signUp(
        name: String?,
        email: String?,
        password: String?,
        confirmPassword: String?,
        confirmPolicy: Boolean
    ): Boolean {
        if (!confirmPolicy) {
            _errorConfirmPolicy.postValue(true)
        }

        val parsedName = parseData(name)
        val isCorrectName = Validator.isValidUserName(parsedName)
        if (!isCorrectName) {
            _errorIncorrectNameInput.postValue(true)
        }
        val isValidSizeName = isValidSizeName(parsedName)

        if (!isValidSizeName) {
            _errorSizeNameInput.postValue(true)
        }

        val parsedEmail = parseData(email)
        val isValidEmail = Validator.isValidEmail(parsedEmail)

        if (!isValidEmail) {
            _errorEmailInput.postValue(true)
        }

        val parsedPassword = parseData(password)
        val parsedConfirmPassword = parseData(confirmPassword)

        val isValidPassword = isValidPassword(parsedPassword)
        if (!isValidPassword) {
            _errorPasswordInput.postValue(true)
        }
        val arePasswordsEqual = parsedPassword == parsedConfirmPassword
        if (!arePasswordsEqual) {
            _errorPasswordsEqualInput.postValue(true)
        }

        if (isValidPassword && isValidEmail && isValidSizeName && isCorrectName && arePasswordsEqual) {
            return signUpUseCase(
                parsedName,
                parsedEmail,
                parsedPassword,
                parsedConfirmPassword
            )
        }
        return false
    }


    private fun isValidSizeName(name: String): Boolean {
        return name.length >= MINIMUM_LENGTH_USER_NAME
    }

    private fun parseData(data: String?): String {
        return data?.trim() ?: ""
    }

    private fun isValidPassword(password: String): Boolean {
        return password.length >= MINIMUM_LENGTH_PASSWORD
    }

    fun resetEmailError() {
        _errorEmailInput.postValue(false)
    }

    fun resetPasswordError() {
        _errorPasswordInput.postValue(false)
    }

    fun resetSizeNameInputError() {
        _errorSizeNameInput.postValue(false)
    }

    fun resetCorrectNameInputError() {
        _errorIncorrectNameInput.postValue(false)
    }

    fun resetPasswordsEqualInputError() {
        _errorPasswordsEqualInput.postValue(false)
    }
}