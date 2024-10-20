package com.imperatorofdwelling.android.domain.cities.usecases

import com.imperatorofdwelling.android.domain.cities.entities.City
import com.imperatorofdwelling.android.domain.cities.repositories.CitiesRepository

class SearchCityUseCase(private val repository: CitiesRepository) {
    operator fun invoke(name: String): List<City>{
        return repository.getCities(name)
    }
}