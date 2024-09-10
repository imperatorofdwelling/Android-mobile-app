package com.imperatorofdwelling.android.domain.usecases

class SignUpUseCase {
    operator fun invoke(name: String, email: String, password: String, confirmPassword: String): Boolean{
        return false
    }
}