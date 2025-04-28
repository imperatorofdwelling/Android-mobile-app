package com.imperatorofdwelling.android.data.repositories

import com.imperatorofdwelling.android.data.entities.mapper.toDomain
import com.imperatorofdwelling.android.data.entities.mapper.toUserEditData
import com.imperatorofdwelling.android.data.local.preferences.RoleManager
import com.imperatorofdwelling.android.data.local.preferences.SharedPreferencesDataSource
import com.imperatorofdwelling.android.data.net.ApiClient
import com.imperatorofdwelling.android.data.utils.CookieManager
import com.imperatorofdwelling.android.domain.NetworkResult
import com.imperatorofdwelling.android.domain.user.entities.Avatar
import com.imperatorofdwelling.android.domain.user.entities.UserDomain
import com.imperatorofdwelling.android.domain.user.repositories.UserRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody

class UserRepositoryImpl(
    private val cookieManager: CookieManager,
    private val sharedPreferencesDataSource: SharedPreferencesDataSource,
    private val userRoleManager: RoleManager,
) : UserRepository {

    override suspend fun isRegistered(): Boolean {
        val cookie = cookieManager.getCookie()

        val result = ApiClient.getFavourites().getAllFavourites(cookie).execute()
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

    override suspend fun editUserData(userDomain: UserDomain): NetworkResult<UserDomain> {
        val userId = sharedPreferencesDataSource.getString(AuthRepositoryImpl.ID_KEY, "")
        val result =
            ApiClient.getUser().editData(userId, userDomain.toUserEditData(), cookieManager.getCookie())
                .execute()
        return if (result.isSuccessful && result.body()?.data != null) {
            NetworkResult.Success(value = result.body()!!.data!!.toDomain())
        } else {
            NetworkResult.Error(errorMessage = "${result.errorBody()?.string()}, $result")
        }
    }

    override suspend fun getUserAvatar(): NetworkResult<String> {
        val result = ApiClient.getUser().getAvatar(cookieManager.getCookie()).execute()
        return if (result.isSuccessful) {
            if (result.body()?.data != null && result.body()?.data != "") {
                NetworkResult.Success(value = ApiClient.BASE_FILE_URL + result.body()?.data)
            } else {
                NetworkResult.Success(value = "")
            }
        } else {
            NetworkResult.Error(errorMessage = "${result.errorBody()?.string()}, $result")
        }
    }

    override suspend fun setUserRole(role: Int) {
        userRoleManager.setUserRole(role)
    }

    override suspend fun getUserRole() = userRoleManager.getUserRoleFlow()

    override suspend fun editUserAvatar(avatar: Avatar): NetworkResult<Boolean> {

        val requestData = avatar.bytes.toRequestBody(avatar.mimeType.toMediaTypeOrNull())
        val multipart = MultipartBody.Part.createFormData("image", avatar.name, requestData)
        val result = ApiClient.getUser().editAvatar(
            cookies = cookieManager.getCookie(),
            image = multipart,
        ).execute()

        return if (result.isSuccessful) {
            NetworkResult.Success(true)
        } else {
            NetworkResult.Error(errorMessage = "${result.errorBody()?.string()}, $result")
        }
    }
}