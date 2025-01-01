package com.imperatorofdwelling.android.data.repositories

import com.imperatorofdwelling.android.data.entities.mapper.FavouritesDomainMapper
import com.imperatorofdwelling.android.data.net.ApiClient
import com.imperatorofdwelling.android.data.utils.CookieManager
import com.imperatorofdwelling.android.domain.NetworkResult
import com.imperatorofdwelling.android.domain.favorites.repositories.FavouritesRepository
import com.imperatorofdwelling.android.domain.stays.entities.Stay

class FavouritesRepositoryImpl(
    private val cookieManager: CookieManager
): FavouritesRepository {
    override fun addToFavourites(stayId: String): NetworkResult<Boolean> {
        val cookie = cookieManager.getCookie()
        val result = ApiClient
            .getFavourites()
            .addToFavourites(stayId, cookie)
            .execute()
        if (result.isSuccessful) {
            return NetworkResult.Success(true)
        }
        return NetworkResult.Error("${result.errorBody()?.string()}, $result")

    }

    override fun deleteFavourites(stayId: String): NetworkResult<Boolean> {
        val cookie = cookieManager.getCookie()
        val result = ApiClient
            .getFavourites()
            .deleteFavourites(stayId, cookie)
            .execute()
        if (result.isSuccessful) {
            return NetworkResult.Success(true)
        }
        return NetworkResult.Error("${result.errorBody()?.string()}, $result")

    }


    override fun getAllFavourites(): NetworkResult<Map<String, List<Stay>>> {
        val cookie = cookieManager.getCookie()

        val result = ApiClient
            .getFavourites()
            .getAllFavourites(cookie)
            .execute()
        if (result.isSuccessful) {
            return NetworkResult.Success(
                FavouritesDomainMapper.transformMap(result.body()?.data ?: emptyMap())
            )
        }
        return NetworkResult.Error("${result.errorBody()?.string()}, $result")
    }
}