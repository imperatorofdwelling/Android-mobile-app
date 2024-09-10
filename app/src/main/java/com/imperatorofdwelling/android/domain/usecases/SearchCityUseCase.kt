package com.imperatorofdwelling.android.domain.usecases

import com.imperatorofdwelling.android.domain.entities.City
import com.imperatorofdwelling.android.domain.repositories.CitiesRepository

class SearchCityUseCase(private val repository: CitiesRepository) {
    operator fun invoke(name: String): List<City>{
        return repository.getCities(name)
    }
}