package com.imperatorofdwelling.android.data.repositories

import android.util.Log
import com.imperatorofdwelling.android.data.local.preferences.SharedPreferencesDataSource
import com.imperatorofdwelling.android.domain.user.repositories.UserRepository

class UserRepositoryImpl(private val sharedPreferencesDataSource: SharedPreferencesDataSource): UserRepository {
    override suspend fun isRegistered(): Boolean {
        val jwt = sharedPreferencesDataSource.getString(AuthRepositoryImpl.JWT_KEY, "")
        Log.e("jwt user res", jwt.isNotBlank().toString())
        return jwt.isNotBlank()
    }

    override suspend fun getToken(): String {
        val jwt = sharedPreferencesDataSource.getString(AuthRepositoryImpl.JWT_KEY, "")
        return jwt
    }
}