package com.imperatorofdwelling.android.domain.locations.usecases

import com.imperatorofdwelling.android.domain.NetworkResult
import com.imperatorofdwelling.android.domain.locations.entities.SearchResult
import com.imperatorofdwelling.android.domain.locations.repositories.LocationRepository

class SearchAddressUseCase(private val repository: LocationRepository) {
    suspend operator fun invoke(q: String): NetworkResult<List<SearchResult>>{
        return repository.getAddress(q)
    }
}