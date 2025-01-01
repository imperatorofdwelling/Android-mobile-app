package com.imperatorofdwelling.android.domain.favorites.repositories

import com.imperatorofdwelling.android.domain.NetworkResult
import com.imperatorofdwelling.android.domain.stays.entities.Stay

interface FavouritesRepository {
    fun addToFavourites(stayId: String): NetworkResult<Boolean>
    fun deleteFavourites(stayId: String): NetworkResult<Boolean>
    fun getAllFavourites(): NetworkResult<Map<String, List<Stay>>>
}