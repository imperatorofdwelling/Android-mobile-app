package com.imperatorofdwelling.android.domain.locations.usecases

import com.imperatorofdwelling.android.domain.locations.entities.SearchResult
import com.imperatorofdwelling.android.domain.locations.repositories.LocationRepository
import kotlinx.coroutines.flow.Flow

class GetSavedAddressUseCase(private val repository: LocationRepository) {
    operator fun invoke(): Flow<SearchResult?> {
        return repository.getSavedAddress()
    }
}