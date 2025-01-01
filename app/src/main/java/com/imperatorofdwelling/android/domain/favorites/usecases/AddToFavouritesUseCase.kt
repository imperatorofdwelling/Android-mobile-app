package com.imperatorofdwelling.android.domain.favorites.usecases

import com.imperatorofdwelling.android.domain.NetworkResult
import com.imperatorofdwelling.android.domain.favorites.repositories.FavouritesRepository

class AddToFavouritesUseCase(private val repository: FavouritesRepository) {
    operator fun invoke(stayId: String): NetworkResult<Boolean> {
        return repository.addToFavourites(stayId)
    }
}