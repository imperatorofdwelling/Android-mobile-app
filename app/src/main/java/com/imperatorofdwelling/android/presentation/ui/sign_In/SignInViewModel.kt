package com.imperatorofdwelling.android.presentation.ui.sign_In

import androidx.compose.runtime.Immutable
import com.imperatorofdwelling.android.domain.usecases.SignInUseCase
import com.imperatorofdwelling.android.presentation.ui.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

private const val MINIMUM_LENGTH_PASSWORD = 8

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
) : BaseViewModel<SignInViewModel.State>(State()) {

    fun onEmailChange(email: String) {
        _state.update { it.copy(email = email) }
    }

    fun onPasswordChange(password: String) {
        _state.update { it.copy(password = password) }
    }

    fun onGoogleLoginClick() {}

    fun onTwitterLoginClick() {}

    fun onSignInClick() {}

    @Immutable
    data class State(
        val email: String = "",
        val password: String = ""
    )
}