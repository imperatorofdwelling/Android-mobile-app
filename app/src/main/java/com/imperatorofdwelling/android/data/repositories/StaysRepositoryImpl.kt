package com.imperatorofdwelling.android.data.repositories

import com.imperatorofdwelling.android.data.entities.mapper.StayDomainMapper
import com.imperatorofdwelling.android.data.net.ApiClient
import com.imperatorofdwelling.android.domain.auth.entities.NetworkResult
import com.imperatorofdwelling.android.domain.stays.entities.Stay
import com.imperatorofdwelling.android.domain.stays.repositories.StaysRepository

class StaysRepositoryImpl : StaysRepository {
    override fun getAllStays(): NetworkResult<List<Stay>> {
        val result = ApiClient.getStay().getStays().execute()
        if (result.isSuccessful) {
            val bodyResult = result.body()
            val mappedResult = StayDomainMapper.transform(bodyResult?.data)
            return NetworkResult.Success(mappedResult)
        } else {
            return NetworkResult.Error("${result.errorBody()?.string()}, $result")
        }
    }
}