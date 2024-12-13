package com.imperatorofdwelling.android.domain.locations.usecases

import com.imperatorofdwelling.android.domain.locations.entities.City
import com.imperatorofdwelling.android.domain.locations.repositories.LocationRepository

class GetDefaultCityUseCase(private val repository: LocationRepository) {
    operator fun invoke(): City?{
        return repository.getDefaultCity()
    }
}