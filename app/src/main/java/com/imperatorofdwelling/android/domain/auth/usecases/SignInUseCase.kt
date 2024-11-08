package com.imperatorofdwelling.android.domain.auth.usecases

import com.imperatorofdwelling.android.domain.auth.repositories.AuthRepository

class SignInUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): Boolean {
        return repository.login(email.trim(), password.trim())
    }
}