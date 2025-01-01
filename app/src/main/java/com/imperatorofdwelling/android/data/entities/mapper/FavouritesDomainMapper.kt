package com.imperatorofdwelling.android.data.entities.mapper

import com.imperatorofdwelling.android.data.entities.FavouritesData
import com.imperatorofdwelling.android.data.entities.StayData
import com.imperatorofdwelling.android.domain.favorites.entities.Favourites
import com.imperatorofdwelling.android.domain.stays.entities.Stay

object FavouritesDomainMapper {
    fun transform(item: FavouritesData?): Favourites? {
        return item?.let {
            Favourites(
                createdAt = it.createdAt,
                updatedAt = it.updatedAt,
                stayId = it.stayId,
                description = it.description,
                userId = it.userId
            )
        }
    }

    fun transform(stayDataCollection: Collection<FavouritesData>?): List<Favourites> {
        return stayDataCollection?.mapNotNull { transform(it) } ?: emptyList()
    }

    fun transformMap(stayDataMap: Map<String, List<StayData>>): Map<String, List<Stay>>{
        val result:  HashMap<String, List<Stay>> = hashMapOf()
        stayDataMap.keys.map{ item ->
            result[item] = transformStayCollection(stayDataMap[item])
        }
        return result
    }

    fun transformStayCollection(stayDataCollection: Collection<StayData>?): List<Stay> {
        return stayDataCollection?.mapNotNull {
            StayDomainMapper.transform(
                it,
                true
            )
        } ?: emptyList()
    }
}