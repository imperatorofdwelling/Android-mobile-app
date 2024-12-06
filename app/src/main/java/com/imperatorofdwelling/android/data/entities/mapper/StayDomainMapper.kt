package com.imperatorofdwelling.android.data.entities.mapper

import com.imperatorofdwelling.android.data.entities.StayData
import com.imperatorofdwelling.android.domain.stays.entities.Stay

object StayDomainMapper {
    fun transform(item: StayData?): Stay? {
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
                updatedAt = it.updatedAt
            )
        }
    }

    fun transform(stayDataCollection: Collection<StayData>?): List<Stay> {
        return stayDataCollection?.mapNotNull { transform(it) } ?: emptyList()
    }
}