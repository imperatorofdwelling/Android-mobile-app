package com.imperatorofdwelling.android.domain.stays.repositories

import com.imperatorofdwelling.android.domain.auth.entities.NetworkResult
import com.imperatorofdwelling.android.domain.stays.entities.Stay

interface StaysRepository {
    fun getAllStays(): NetworkResult<List<Stay>>
    fun getMainImage(id: String): NetworkResult<String>
}