package com.imperatorofdwelling.android.domain.stays.repositories

import com.imperatorofdwelling.android.domain.NetworkResult
import com.imperatorofdwelling.android.domain.stays.entities.Stay
import com.imperatorofdwelling.android.domain.user.entities.Avatar
import kotlinx.coroutines.flow.Flow

interface StaysRepository {
    fun getAllStays(): NetworkResult<List<Stay>>
    fun getStaysByLocation(locationId: String): NetworkResult<List<Stay>>
    fun getMainImage(id: String): NetworkResult<String>
    fun getStayById(id: String): NetworkResult<Stay>
    fun getCreatingHelp(): Flow<Boolean>
    fun updateCreatingHelp(value: Boolean)
    fun getAllAmenities(): NetworkResult<List<String>>
    fun createStay(stay: Stay): NetworkResult<String>
    fun createStayImage(image: Avatar, stayId: String): NetworkResult<String>
    fun getStaysByUserId(): NetworkResult<List<Stay>>
    fun deleteStay(stayId: String): NetworkResult<String>
}