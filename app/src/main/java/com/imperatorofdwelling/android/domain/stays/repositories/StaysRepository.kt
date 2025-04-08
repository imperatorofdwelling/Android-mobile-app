package com.imperatorofdwelling.android.domain.stays.repositories

import com.imperatorofdwelling.android.domain.NetworkResult
import com.imperatorofdwelling.android.domain.stays.entities.Stay
import kotlinx.coroutines.flow.Flow

interface StaysRepository {
    fun getAllStays(): NetworkResult<List<Stay>>
    fun getStaysByLocation(locationId: String): NetworkResult<List<Stay>>
    fun getMainImage(id: String): NetworkResult<String>
    fun getStayById(id: String): NetworkResult<Stay>
    fun getCreatingHelp(): Flow<Boolean>
    fun updateCreatingHelp(value: Boolean)
}