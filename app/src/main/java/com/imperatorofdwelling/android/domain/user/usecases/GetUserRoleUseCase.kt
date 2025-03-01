package com.imperatorofdwelling.android.domain.user.usecases

import com.imperatorofdwelling.android.domain.user.repositories.UserRepository
import kotlinx.coroutines.flow.Flow

class GetUserRoleUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(): Flow<String> {
        return repository.getUserRole()
    }
}