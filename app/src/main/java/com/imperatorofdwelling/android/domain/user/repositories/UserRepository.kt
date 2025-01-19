package com.imperatorofdwelling.android.domain.user.repositories

import com.imperatorofdwelling.android.domain.NetworkResult
import com.imperatorofdwelling.android.domain.user.entities.UserDomain

interface UserRepository {
    suspend fun isRegistered(): Boolean
    suspend fun getToken(): String
    suspend fun getUserData(): NetworkResult<UserDomain>
}