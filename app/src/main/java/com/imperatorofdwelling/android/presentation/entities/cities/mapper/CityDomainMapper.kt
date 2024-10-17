package com.imperatorofdwelling.android.presentation.entities.cities.mapper

import com.imperatorofdwelling.android.domain.cities.entities.City
import com.imperatorofdwelling.android.presentation.entities.cities.CityViewModelEntity

object CityDomainMapper {
    fun transform(item: CityViewModelEntity?): City? {
        return item?.let{
            City(
                id = City.UNKNOWN_ID,
                name = item.name
            )
        }
    }

    fun transform(items: Collection<CityViewModelEntity>?): List<City>{
        return items?.mapNotNull{ transform(it) } ?: emptyList()
    }
}