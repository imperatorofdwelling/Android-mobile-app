package com.imperatorofdwelling.android.domain.user.repositories

interface UserRepository {
    suspend fun isRegistered(): Boolean
    suspend fun getToken(): String
}