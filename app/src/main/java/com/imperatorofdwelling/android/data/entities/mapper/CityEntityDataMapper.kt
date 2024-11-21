package com.imperatorofdwelling.android.data.entities.mapper

import com.imperatorofdwelling.android.data.entities.CityEntity
import com.imperatorofdwelling.android.domain.cities.entities.City

object CityEntityDataMapper {

    fun transform(cityEntity: CityEntity?): City? {
        return cityEntity?.let {
            City(
                id = it.id,
                name = it.name,
                population = it.population
            )
        }
    }

    fun transform(cityEntityCollection: Collection<CityEntity>?): List<City> {
        return cityEntityCollection?.mapNotNull { transform(it) } ?: emptyList()
    }

}