package com.imperatorofdwelling.android.data.entities.mapper

import com.imperatorofdwelling.android.data.entities.CityEntity
import com.imperatorofdwelling.android.domain.cities.entities.City

object CityEntityDomainMapper {

    fun transform(city: City?): CityEntity? {
        return city?.let {
            CityEntity(
                id = it.id,
                name = it.name
            )
        }
    }
}