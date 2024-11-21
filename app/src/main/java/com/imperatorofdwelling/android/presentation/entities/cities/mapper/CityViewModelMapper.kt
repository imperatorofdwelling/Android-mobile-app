package com.imperatorofdwelling.android.presentation.entities.cities.mapper

import com.imperatorofdwelling.android.domain.cities.entities.City
import com.imperatorofdwelling.android.presentation.entities.cities.CityViewModelEntity

object CityViewModelMapper {
    fun transform(city: City?): CityViewModelEntity? {
        return city?.let {
            CityViewModelEntity(
                name = it.name,
                population = it.population
            )
        }
    }
    fun transform(cityList: List<City>?): List<CityViewModelEntity> {
        return cityList?.mapNotNull { transform(it) } ?: emptyList()
    }

}