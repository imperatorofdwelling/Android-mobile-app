package com.imperatorofdwelling.android.data.entities.mapper

import com.imperatorofdwelling.android.data.entities.StayData
import com.imperatorofdwelling.android.domain.stays.entities.Stay

object StayDomainMapper {
    fun transform(item: StayData?, isFavourites: Boolean): Stay? {
        return item?.let {
            Stay(
                id = it.id,
                userId = it.userId,
                locationId = it.locationId,
                name = it.name,
                type = it.type,
                numberOfBedrooms = it.numberOfBedrooms,
                numberOfBeds = it.numberOfBeds,
                numberOfBathrooms = it.numberOfBathrooms,
                guests = it.guests,
                rating = it.rating,
                isSmokingProhibited = it.isSmokingProhibited,
                square = it.square,
                street = it.street,
                house = it.house,
                entrance = it.entrance,
                floor = it.floor,
                room = it.room,
                price = it.price,
                createdAt = it.createdAt,
                updatedAt = it.updatedAt,
                isFavourites = isFavourites
            )
        }
    }

    fun transform(stayDataCollection: Collection<StayData>?, favouritesData: Set<String>): List<Stay> {
        return stayDataCollection?.mapNotNull { transform(it, it.id in favouritesData) } ?: emptyList()
    }
}