package com.imperatorofdwelling.android.domain.auth.repositories

interface AuthRepository {
    suspend fun register(name: String, email: String, password: String): Boolean
    suspend fun login(email: String, password: String): Boolean
    suspend fun recoverPassword(email: String): Boolean
}