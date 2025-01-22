package com.imperatorofdwelling.android.domain.auth.usecases

import com.imperatorofdwelling.android.domain.auth.repositories.AuthRepository

class LogOutUseCase(private val repository: AuthRepository) {
    operator fun invoke() {
        repository.logOut()
    }
}