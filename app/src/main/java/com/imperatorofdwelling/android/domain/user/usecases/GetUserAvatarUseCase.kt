package com.imperatorofdwelling.android.domain.user.usecases

import com.imperatorofdwelling.android.domain.NetworkResult
import com.imperatorofdwelling.android.domain.user.repositories.UserRepository

class GetUserAvatarUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(): NetworkResult<String> {
        return repository.getUserAvatar()
    }
}