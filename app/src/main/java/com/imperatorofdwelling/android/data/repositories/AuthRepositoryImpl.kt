package com.imperatorofdwelling.android.data.repositories

import com.imperatorofdwelling.android.domain.auth.AuthRepository

class AuthRepositoryImpl : AuthRepository {
    override suspend fun login(email: String, password: String): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun register(name: String, email: String, password: String): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun recoverPassword(email: String): Result<Unit> {
        TODO("Not yet implemented")
    }
}