package com.imperatorofdwelling.android.domain.auth.repositories

import com.imperatorofdwelling.android.domain.auth.entities.NetworkResult

interface AuthRepository {
    suspend fun register(name: String, email: String, password: String): NetworkResult<Boolean>
    suspend fun login(email: String, password: String): NetworkResult<Boolean>
    suspend fun recoverPassword(email: String): NetworkResult<Boolean>
}