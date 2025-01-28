package com.imperatorofdwelling.android.domain.user.usecases

import com.imperatorofdwelling.android.domain.NetworkResult
import com.imperatorofdwelling.android.domain.user.entities.UserDomain
import com.imperatorofdwelling.android.domain.user.repositories.UserRepository

class EditUserDataUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(userDomain: UserDomain): NetworkResult<UserDomain>{
        return repository.editUserData(userDomain)
    }
}