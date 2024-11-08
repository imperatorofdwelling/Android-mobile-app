package com.imperatorofdwelling.android.data.repositories

import com.imperatorofdwelling.android.data.local.preferences.SharedPreferencesDataSource
import com.imperatorofdwelling.android.data.net.ApiClient
import com.imperatorofdwelling.android.data.utils.CookieParser
import com.imperatorofdwelling.android.domain.auth.entities.LoginData
import com.imperatorofdwelling.android.domain.auth.entities.RegistrationData
import com.imperatorofdwelling.android.domain.auth.repositories.AuthRepository

class AuthRepositoryImpl(private val sharedPreferencesDataSource: SharedPreferencesDataSource) :
    AuthRepository {
    companion object {
        private const val JWT_KEY = "jwt"
        private const val ID_KEY = "id"
    }

    override suspend fun login(email: String, password: String): Boolean {
        val result = ApiClient
            .getUser()
            .login(LoginData(email, password))
            .execute()

        if (result.isSuccessful) {
            val cookies = result.headers().get("Set-Cookie")
            val jwtToken =
                CookieParser.extractJwtToken(cookies) ?: return false
            val id = result.body().toString()

            sharedPreferencesDataSource.putString(JWT_KEY, jwtToken)
            sharedPreferencesDataSource.putString(ID_KEY, id)
        } else {
            throw RuntimeException("${result.errorBody()?.string()}, ${result}")
        }
        return result.isSuccessful

    }


    override suspend fun register(name: String, email: String, password: String): Boolean {
        val result = ApiClient
            .getUser()
            .registration(RegistrationData(name = name, email = email, password = password))
            .execute()

        if (result.isSuccessful) {
            return login(email, password)
        } else {
            throw RuntimeException(result.errorBody()?.string())
        }
    }

    override suspend fun recoverPassword(email: String): Boolean {
        return true
    }
}
