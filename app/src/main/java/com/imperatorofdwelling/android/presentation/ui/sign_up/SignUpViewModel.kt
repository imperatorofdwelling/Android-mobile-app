package com.imperatorofdwelling.android.presentation.ui.sign_up

import androidx.compose.runtime.Immutable
import com.imperatorofdwelling.android.domain.auth.usecases.SignUpUseCase
import com.imperatorofdwelling.android.presentation.ui.common.BaseViewModel
import kotlinx.coroutines.flow.update

private const val MINIMUM_LENGTH_USER_NAME = 3
private const val MINIMUM_LENGTH_PASSWORD = 8

class SignUpViewModel(
    private val signUpUseCase: SignUpUseCase
) : BaseViewModel<SignUpViewModel.State>(State()) {

    fun onNameChange(name: String) {
        _state.update { it.copy(name = name) }
    }

    fun onEmailChange(email: String) {
        _state.update { it.copy(email = email) }
    }

    fun onPasswordChange(password: String) {
        _state.update { it.copy(password = password) }
    }

    fun onConfirmPasswordChange(confirmPassword: String) {
        _state.update { it.copy(confirmPassword = confirmPassword) }
    }

    fun onAgreedToTermsChange(agreedToTerms: Boolean) {
        _state.update { it.copy(agreedToTerms = agreedToTerms) }
    }

    fun onGoogleLoginClick() {}

    fun onTwitterLoginClick() {}

    fun onSignUpClick() {}

    @Immutable
    data class State(
        val name: String = "",
        val email: String = "",
        val password: String = "",
        val confirmPassword: String = "",
        val agreedToTerms: Boolean = false
    )
}