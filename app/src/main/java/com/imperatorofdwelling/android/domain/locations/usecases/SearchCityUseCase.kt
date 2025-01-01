package com.imperatorofdwelling.android.domain.locations.usecases

import com.imperatorofdwelling.android.domain.NetworkResult
import com.imperatorofdwelling.android.domain.locations.entities.City
import com.imperatorofdwelling.android.domain.locations.repositories.LocationRepository

class SearchCityUseCase(private val repository: LocationRepository) {
    operator fun invoke(name: String): NetworkResult<List<City>> {
        return repository.getCities(name)
    }
}