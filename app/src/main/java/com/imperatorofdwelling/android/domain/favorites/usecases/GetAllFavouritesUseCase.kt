package com.imperatorofdwelling.android.domain.favorites.usecases

import com.imperatorofdwelling.android.domain.NetworkResult
import com.imperatorofdwelling.android.domain.favorites.repositories.FavouritesRepository
import com.imperatorofdwelling.android.domain.stays.entities.Stay

class GetAllFavouritesUseCase(private val repository: FavouritesRepository) {
    operator fun invoke(): NetworkResult<Map<String, List<Stay>>> {
        return repository.getAllFavourites()
    }
}