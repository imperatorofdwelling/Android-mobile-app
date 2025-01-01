package com.imperatorofdwelling.android.presentation.ui.sign_In

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.lifecycle.viewModelScope
import com.imperatorofdwelling.android.domain.NetworkResult
import com.imperatorofdwelling.android.domain.auth.usecases.SignInUseCase
import com.imperatorofdwelling.android.presentation.ui.common.BaseViewModel
import com.imperatorofdwelling.android.presentation.ui.utils.Validator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val MINIMUM_LENGTH_PASSWORD = 8

class SignInViewModel(
    private val signInUseCase: SignInUseCase,
) : BaseViewModel<SignInViewModel.State>(State()) {


    fun onEmailChange(email: String) {
        _state.update { it.copy(email = email) }
        _state.update { it.copy(emailError = !Validator.isValidEmail(email.trim())) }
        clearServerError()
    }

    fun onPasswordChange(password: String) {
        _state.update { it.copy(password = password) }
        _state.update { it.copy(passwordError = password.length < MINIMUM_LENGTH_PASSWORD) }
        clearServerError()
    }


    private fun clearServerError() {
        _state.update { it.copy(serverHasError = false) }
    }

    fun onGoogleLoginClick() {}

    fun onTwitterLoginClick() {}

    fun onSignInClick(callBackOnCompletion: () -> Unit) {
        if (!hasAnyError() && !isEmptyFieldExist()) {
            var authResultFlag = false
            viewModelScope.launch(Dispatchers.IO) {
                runCatching {
                    val authResult = signInUseCase(
                        email = _state.value.email,
                        password = _state.value.password
                    )
                    when (authResult) {
                        is NetworkResult.Success -> authResultFlag = authResult.value
                        is NetworkResult.Error -> {
                            _state.update {
                                it.copy(
                                    serverHasError = true
                                )
                            }
                            Log.e("AuthError", authResult.errorMessage)
                        }
                    }
                }.onFailure { e ->
                    Log.e("Auth Error", e.message.toString())
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
                _state.value.passwordError

    fun isEmptyFieldExist(): Boolean {
        return _state.value.password.isEmpty() ||
                _state.value.email.isEmpty()
    }

    @Immutable
    data class State(
        val email: String = "",
        val password: String = "",
        val emailError: Boolean = false,
        val passwordError: Boolean = false,
        val serverHasError: Boolean = false,
    )
}