package com.imperatorofdwelling.android.domain.use_cases

import com.imperatorofdwelling.android.domain.models.City
import com.imperatorofdwelling.android.domain.repositories.CitiesRepository

class SearchCityUseCase(private val repository: CitiesRepository) {
    fun execute(name: String): List<City>{
        return repository.getCities(name)
    }
}