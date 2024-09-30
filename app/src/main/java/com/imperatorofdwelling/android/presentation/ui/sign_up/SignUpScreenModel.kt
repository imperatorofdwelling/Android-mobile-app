package com.imperatorofdwelling.android.presentation.ui.sign_up

import androidx.compose.runtime.Immutable
import com.imperatorofdwelling.android.domain.auth.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class SignUpScreenModel(
    private val repository: AuthRepository
) {
    private val _state = MutableStateFlow(State())
    val state = _state

    fun onRegisterFieldsChange(
        name: String = _state.value.name,
        email: String = _state.value.email,
        password: String = _state.value.password,
        confirmPassword: String = _state.value.confirmPassword,
        agreedToTerms: Boolean = _state.value.agreedToTerms
    ) {
        _state.update {
            it.copy(
                name = name,
                email = email,
                password = password,
                confirmPassword = confirmPassword,
                agreedToTerms = agreedToTerms
            )
        }
    }

    fun onRegisterClick() {
        TODO("Not yet implemented")
    }

    @Immutable
    data class State(
        val name: String = "",
        val email: String = "",
        val password: String = "",
        val confirmPassword: String = "",
        val agreedToTerms: Boolean = false
    )
}