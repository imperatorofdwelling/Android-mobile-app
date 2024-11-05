package com.imperatorofdwelling.android.domain.auth.repositories

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<Unit>
    suspend fun register(name: String, email: String, password: String): Result<Unit>
    suspend fun recoverPassword(email: String): Result<Unit>
}