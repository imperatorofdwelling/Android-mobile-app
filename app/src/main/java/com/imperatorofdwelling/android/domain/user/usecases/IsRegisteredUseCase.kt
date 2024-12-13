package com.imperatorofdwelling.android.domain.user.usecases

import com.imperatorofdwelling.android.domain.user.repositories.UserRepository

class IsRegisteredUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(): Boolean {
        return repository.isRegistered()
    }
}