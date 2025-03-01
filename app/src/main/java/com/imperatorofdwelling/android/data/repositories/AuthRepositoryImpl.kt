package com.imperatorofdwelling.android.data.repositories

import com.imperatorofdwelling.android.data.local.preferences.SharedPreferencesDataSource
import com.imperatorofdwelling.android.data.net.ApiClient
import com.imperatorofdwelling.android.data.utils.CookieParser
import com.imperatorofdwelling.android.data.utils.PasswordManager
import com.imperatorofdwelling.android.domain.NetworkResult
import com.imperatorofdwelling.android.domain.auth.entities.LoginData
import com.imperatorofdwelling.android.domain.auth.entities.RegistrationData
import com.imperatorofdwelling.android.domain.auth.repositories.AuthRepository

class AuthRepositoryImpl(private val sharedPreferencesDataSource: SharedPreferencesDataSource) :
    AuthRepository {
    companion object {
        const val ROLE_KEY = "role"
        const val JWT_KEY = "jwt"
        const val ID_KEY = "id"
    }

    override suspend fun login(email: String, password: String): NetworkResult<Boolean> {
        val hashedPassword = PasswordManager.sha256(password)

        val result = ApiClient
            .getUser()
            .login(LoginData(email, hashedPassword, isHashed = true))
            .execute()

        if (result.isSuccessful) {
            val cookies = result.headers()["Set-Cookie"]
            val jwtToken =
                CookieParser.extractJwtToken(cookies) ?: return NetworkResult.Success(false)

            val id = result.body()?.data ?: ""

            sharedPreferencesDataSource.putString(JWT_KEY, jwtToken)
            sharedPreferencesDataSource.putString(ID_KEY, id)
            return NetworkResult.Success(true)
        } else {
            return NetworkResult.Error("${result.errorBody()?.string()}, $result")
        }
    }

    override fun logOut() {
        sharedPreferencesDataSource.remove(JWT_KEY)
        sharedPreferencesDataSource.remove(ID_KEY)
    }

    override suspend fun register(
        name: String,
        email: String,
        password: String
    ): NetworkResult<Boolean> {
        val hashedPassword = PasswordManager.sha256(password)
        val result = ApiClient
            .getUser()
            .registration(
                RegistrationData(
                    name = name,
                    email = email,
                    password = hashedPassword,
                    isHashed = true
                )
            )
            .execute()
        return if (result.isSuccessful) {
            login(email, password)
        } else {
            NetworkResult.Error(result.errorBody()?.string() ?: "")
        }
    }

    override suspend fun recoverPassword(email: String): NetworkResult<Boolean> {
        return NetworkResult.Error("")
    }


}
