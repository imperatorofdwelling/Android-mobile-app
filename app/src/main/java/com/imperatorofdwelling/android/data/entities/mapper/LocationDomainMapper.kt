package com.imperatorofdwelling.android.data.entities.mapper

import com.imperatorofdwelling.android.data.entities.location.LocationData
import com.imperatorofdwelling.android.domain.locations.entities.City

object LocationDomainMapper {
    fun transform(item: LocationData?): City? {
        return item?.let {
            City(
                city = it.city,
                createdAt = it.createdAt,
                federalDistrict = it.federalDistrict,
                fiasId = it.fiasId,
                id = it.id,
                kladrId = it.kladrId,
                lat = it.lat,
                lon = it.lon,
                okato = it.okato,
                oktmo = it.oktmo,
                population = it.population,
                regionName = it.regionName,
                regionIsoCode = it.regionIsoCode,
                updatedAt = it.updatedAt
            )
        }
    }

    fun transform(stayDataCollection: Collection<LocationData>?): List<City> {
        return stayDataCollection?.mapNotNull { transform(it) } ?: emptyList()
    }
}