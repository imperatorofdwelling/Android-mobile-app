package com.imperatorofdwelling.android.presentation.ui.signIn

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.imperatorofdwelling.android.domain.usecases.SignInUseCase
import com.imperatorofdwelling.android.presentation.ui.utils.Validator

class SignInViewModel : ViewModel() {

    companion object {
        private const val MINIMUM_LENGTH_PASSWORD = 8
    }

    private val _errorEmailInput = MutableLiveData<Boolean>()
    val errorEmailInput: LiveData<Boolean>
        get() = _errorEmailInput

    private val _errorPasswordInput = MutableLiveData<Boolean>()
    val errorPasswordInput: LiveData<Boolean>
        get() = _errorPasswordInput


    private val signInUseCase = SignInUseCase()

    fun signIn(email: String?, password: String?): Boolean {
        val parsePassword = parsePassword(password)
        val parseEmail = parseEmail(email)
        val isCorrectEmail = Validator.isValidEmail(parseEmail)
        val isValidPassword = isValidPassword(parsePassword)
        if (!isCorrectEmail) {
            _errorEmailInput.postValue(true)
        }

        if (!isValidPassword) {
            _errorPasswordInput.postValue(true)
        }

        if (isValidPassword && isCorrectEmail) {
            return signInUseCase.execute(parseEmail, parsePassword)
        }
        return false
    }


    private fun parseEmail(email: String?): String {
        return email?.trim() ?: ""
    }

    private fun isValidPassword(password: String): Boolean {
        return password.length >= MINIMUM_LENGTH_PASSWORD
    }

    private fun parsePassword(password: String?): String {
        return if (password.isNullOrBlank()) {
            ""
        } else {
            password
        }
    }

    fun resetEmailError() {
        _errorEmailInput.postValue(false)
    }

    fun resetPasswordError() {
        _errorPasswordInput.postValue(false)
    }
}