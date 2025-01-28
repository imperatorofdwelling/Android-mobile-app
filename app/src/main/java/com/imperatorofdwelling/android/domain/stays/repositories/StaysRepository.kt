package com.imperatorofdwelling.android.domain.stays.repositories

import com.imperatorofdwelling.android.domain.NetworkResult
import com.imperatorofdwelling.android.domain.stays.entities.Stay

interface StaysRepository {
    fun getAllStays(): NetworkResult<List<Stay>>
    fun getStaysByLocation(locationId: String): NetworkResult<List<Stay>>
    fun getMainImage(id: String): NetworkResult<String>
    fun getStayById(id: String): NetworkResult<Stay>
}