package com.imperatorofdwelling.android.domain.usecases

import com.imperatorofdwelling.android.domain.entities.City
import com.imperatorofdwelling.android.domain.repositories.CitiesRepository

class GetDefaultCityUseCase(private val repository: CitiesRepository) {
    operator fun invoke(): City?{
        return repository.getDefaultCity()
    }
}