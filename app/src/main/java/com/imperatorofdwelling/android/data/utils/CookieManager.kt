package com.imperatorofdwelling.android.data.utils

import com.imperatorofdwelling.android.data.local.preferences.SharedPreferencesDataSource
import com.imperatorofdwelling.android.data.repositories.AuthRepositoryImpl

class CookieManager(private val sharedPreferencesDataSource: SharedPreferencesDataSource) {

    fun getCookie(): String {
        return "jwt-token=${sharedPreferencesDataSource.getString(AuthRepositoryImpl.JWT_KEY)}"
    }

}