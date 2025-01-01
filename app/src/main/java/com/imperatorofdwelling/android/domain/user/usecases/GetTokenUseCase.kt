package com.imperatorofdwelling.android.domain.user.usecases

import com.imperatorofdwelling.android.domain.user.repositories.UserRepository

class GetTokenUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(): String{
        return repository.getToken()
    }
}