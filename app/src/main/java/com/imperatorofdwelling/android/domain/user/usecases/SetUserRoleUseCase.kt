package com.imperatorofdwelling.android.domain.user.usecases

import com.imperatorofdwelling.android.domain.user.repositories.UserRepository

class SetUserRoleUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(role: String){
        repository.setUserRole(role)
    }
}