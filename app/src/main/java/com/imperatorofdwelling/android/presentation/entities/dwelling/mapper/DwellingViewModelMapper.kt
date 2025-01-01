package com.imperatorofdwelling.android.presentation.entities.dwelling.mapper

import com.imperatorofdwelling.android.domain.stays.entities.Stay
import com.imperatorofdwelling.android.presentation.entities.Dwelling
import com.imperatorofdwelling.android.presentation.entities.Euro
import com.imperatorofdwelling.android.presentation.entities.Period
import com.imperatorofdwelling.android.presentation.entities.Price

object DwellingViewModelMapper {
    fun transform(item: Stay?): Dwelling? {
        return item?.let {
            Dwelling(
                id = it.id,
                userId = it.userId,
                locationId = it.locationId,
                name = it.name,
                type = it.type,
                numberOfBedrooms = it.numberOfBedrooms,
                numberOfBeds = it.numberOfBeds,
                numberOfBathrooms = it.numberOfBathrooms,
                guests = it.guests,
                mark = it.rating,
                isSmokingProhibited = it.isSmokingProhibited,
                square = it.square,
                street = it.street,
                house = it.house,
                entrance = it.entrance,
                floor = it.floor,
                room = it.room,
                price = Price(Euro(), it.price, Period.Nightly),
                createdAt = it.createdAt,
                updatedAt = it.updatedAt,
                address = "${it.square}, ${it.street}, ${it.house}",
                isLiked = it.isFavourites
            )
        }
    }

    fun transform(stayCollection: Collection<Stay>?): List<Dwelling> {
        return stayCollection?.mapNotNull { transform(it) } ?: emptyList()
    }

    fun transformMap(stayDataMap: Map<String, List<Stay>>): Map<String, List<Dwelling>>{
        val result:  HashMap<String, List<Dwelling>> = hashMapOf()
        stayDataMap.keys.map{ item ->
            result[item] = transform(stayDataMap[item])
        }
        return result
    }
}