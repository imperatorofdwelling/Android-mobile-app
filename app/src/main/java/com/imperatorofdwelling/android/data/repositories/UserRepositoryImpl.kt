package com.imperatorofdwelling.android.data.repositories

import com.imperatorofdwelling.android.data.entities.mapper.toDomain
import com.imperatorofdwelling.android.data.local.preferences.SharedPreferencesDataSource
import com.imperatorofdwelling.android.data.net.ApiClient
import com.imperatorofdwelling.android.data.utils.CookieManager
import com.imperatorofdwelling.android.domain.NetworkResult
import com.imperatorofdwelling.android.domain.user.entities.UserDomain
import com.imperatorofdwelling.android.domain.user.repositories.UserRepository

class UserRepositoryImpl(
    private val cookieManager: CookieManager,
    private val sharedPreferencesDataSource: SharedPreferencesDataSource
) : UserRepository {

    override suspend fun isRegistered(): Boolean {
        val cookie = cookieManager.getCookie()

        val result = ApiClient
            .getFavourites()
            .getAllFavourites(cookie)
            .execute()
        return result.isSuccessful
    }

    override suspend fun getToken(): String {
        val jwt = sharedPreferencesDataSource.getString(AuthRepositoryImpl.JWT_KEY, "")
        return jwt
    }

    override suspend fun getUserData(): NetworkResult<UserDomain> {
        val userId = sharedPreferencesDataSource.getString(AuthRepositoryImpl.ID_KEY, "")
        val result = ApiClient.getUser().getData(userId).execute()
        return if (result.isSuccessful && result.body()?.data != null) {
            NetworkResult.Success(value = result.body()!!.data!!.toDomain())
        } else {
            NetworkResult.Error(errorMessage = "${result.errorBody()?.string()}, $result")
        }
    }


}