package com.imperatorofdwelling.android.domain.usecases

import com.imperatorofdwelling.android.domain.entities.City
import com.imperatorofdwelling.android.domain.repositories.CitiesRepository

class SetDefaultCityUseCase(private val repository: CitiesRepository) {
    operator fun invoke(city: City){
        repository.setDefaultCity(city)
    }
}