package com.imperatorofdwelling.android.domain.auth.usecases

import com.imperatorofdwelling.android.domain.auth.entities.NetworkResult
import com.imperatorofdwelling.android.domain.auth.repositories.AuthRepository

class SignUpUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ): NetworkResult<Boolean> {
        if (password == confirmPassword) {
            return repository.register(
                name = name.trim(),
                email = email.trim(),
                password = password.trim()
            )
        }
        return NetworkResult.Error("passwords don't match")
    }
}