package com.imperatorofdwelling.android.domain.auth.usecases

import com.imperatorofdwelling.android.domain.auth.entities.NetworkResult
import com.imperatorofdwelling.android.domain.auth.repositories.AuthRepository

class SignInUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): NetworkResult<Boolean> {
        return repository.login(email.trim(), password.trim())
    }
}