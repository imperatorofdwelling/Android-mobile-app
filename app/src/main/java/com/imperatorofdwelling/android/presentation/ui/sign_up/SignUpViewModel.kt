package com.imperatorofdwelling.android.presentation.ui.sign_up

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.lifecycle.viewModelScope
import com.imperatorofdwelling.android.domain.NetworkResult
import com.imperatorofdwelling.android.domain.auth.usecases.SignUpUseCase
import com.imperatorofdwelling.android.presentation.ui.common.BaseViewModel
import com.imperatorofdwelling.android.presentation.ui.utils.ErrorManager
import com.imperatorofdwelling.android.presentation.ui.utils.Validator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val MINIMUM_LENGTH_PASSWORD = 8

class SignUpViewModel(
    private val signUpUseCase: SignUpUseCase,
) : BaseViewModel<SignUpViewModel.State>(State()) {


    fun onNameChange(name: String) {
        _state.update { it.copy(name = name) }
        _state.update { it.copy(nameError = !Validator.isValidUserName(name)) }
        _state.update { it.copy(lengthNameError = !Validator.isValidLengthUserName(name)) }
        clearServerError()
    }

    fun onEmailChange(email: String) {
        _state.update { it.copy(email = email) }
        _state.update { it.copy(emailError = !Validator.isValidEmail(email.trim())) }
        clearServerError()
    }

    fun onPasswordChange(password: String) {
        _state.update { it.copy(password = password) }
        _state.update { it.copy(passwordError = password.trim().length < MINIMUM_LENGTH_PASSWORD) }
        clearServerError()
    }

    fun onConfirmPasswordChange(confirmPassword: String) {
        _state.update { it.copy(confirmPassword = confirmPassword) }
        _state.update { it.copy(confirmPasswordError = confirmPassword != _state.value.password) }
        clearServerError()
    }

    fun onAgreedToTermsChange(agreedToTerms: Boolean) {
        _state.update { it.copy(agreedToTerms = agreedToTerms) }
    }

    private fun clearServerError() {
        _state.update { it.copy(serverTextError = null) }
    }

    fun onGoogleLoginClick() {}

    fun onTwitterLoginClick() {}

    fun onSignUpClick(callBackOnCompletion: () -> Unit) {
        if (!hasAnyError() && !isEmptyFieldExist()) {
            var authResultFlag = false
            viewModelScope.launch(Dispatchers.IO) {
                runCatching {
                    val authResult = signUpUseCase(
                        name = _state.value.name,
                        email = _state.value.email,
                        password = _state.value.password,
                        confirmPassword = _state.value.confirmPassword
                    )
                    when (authResult) {
                        is NetworkResult.Success -> {
                            authResultFlag = authResult.value
                        }

                        is NetworkResult.Error -> {
                            _state.update {
                                it.copy(
                                    serverTextError = ErrorManager.extractErrorMessage(
                                        authResult.errorMessage
                                    )
                                )
                            }
                        }
                    }
                }.onFailure { e ->
                    Log.e("Exception", e.message.toString())
                }
            }.invokeOnCompletion {
                if (authResultFlag) {
                    callBackOnCompletion()
                }
            }
        }
    }


    fun hasAnyError(): Boolean =
        _state.value.emailError ||
                _state.value.passwordError ||
                _state.value.confirmPasswordError ||
                _state.value.nameError

    fun isEmptyFieldExist(): Boolean {
        return _state.value.password.isEmpty() ||
                _state.value.email.isEmpty() ||
                _state.value.name.isEmpty() ||
                _state.value.confirmPassword.isEmpty()
    }

    @Immutable
    data class State(
        val name: String = "",
        val email: String = "",
        val password: String = "",
        val confirmPassword: String = "",
        val agreedToTerms: Boolean = false,
        val nameError: Boolean = false,
        val lengthNameError: Boolean = false,
        val emailError: Boolean = false,
        val passwordError: Boolean = false,
        val confirmPasswordError: Boolean = false,
        val serverTextError: String? = null,
    )
}