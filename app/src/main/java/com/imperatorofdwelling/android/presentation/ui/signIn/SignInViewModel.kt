package com.imperatorofdwelling.android.presentation.ui.signIn

import androidx.lifecycle.ViewModel
import com.imperatorofdwelling.android.domain.usecases.SignInUseCase

class SignInViewModel : ViewModel() {

    private val signInUseCase = SignInUseCase()
    fun signUp(email: String, password: String): Boolean {
        return signInUseCase.execute(email, password)
    }

}