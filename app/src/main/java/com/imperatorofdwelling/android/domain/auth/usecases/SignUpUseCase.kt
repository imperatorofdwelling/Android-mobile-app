package com.imperatorofdwelling.android.domain.auth.usecases

import com.imperatorofdwelling.android.domain.auth.repositories.AuthRepository

class SignUpUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(name: String, email: String, password: String, confirmPassword: String): Boolean{
        if(password == confirmPassword){
            return repository.register(name = name.trim(), email = email.trim(), password = password.trim())
        }
        return false
    }
}