package com.imperatorofdwelling.android.domain.cities.usecases

import com.imperatorofdwelling.android.domain.cities.entities.City
import com.imperatorofdwelling.android.domain.cities.repositories.CitiesRepository

class SetDefaultCityUseCase(private val repository: CitiesRepository) {
    operator fun invoke(city: City){
        repository.setDefaultCity(city)
    }
}