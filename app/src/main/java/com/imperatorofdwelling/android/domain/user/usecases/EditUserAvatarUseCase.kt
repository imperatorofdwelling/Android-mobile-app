package com.imperatorofdwelling.android.domain.user.usecases

import com.imperatorofdwelling.android.domain.NetworkResult
import com.imperatorofdwelling.android.domain.user.entities.Avatar
import com.imperatorofdwelling.android.domain.user.repositories.UserRepository

class EditUserAvatarUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(avatar: Avatar): NetworkResult<Boolean> {
        return repository.editUserAvatar(avatar)
    }
}