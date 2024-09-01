package com.imperatorofdwelling.android.presentation.ui.signup

import androidx.lifecycle.ViewModel
import com.imperatorofdwelling.android.domain.usecases.SignUpUseCase

class SignUpViewModel : ViewModel() {

    private val signUpUseCase = SignUpUseCase()

    fun signUp(name: String, email: String, password: String, confirmPassword: String) : Boolean {
        return signUpUseCase.execute(name, email, password, confirmPassword)
    }
}