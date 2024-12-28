package com.imperatorofdwelling.android.domain.stays.usecases

import com.imperatorofdwelling.android.domain.NetworkResult
import com.imperatorofdwelling.android.domain.stays.entities.Stay
import com.imperatorofdwelling.android.domain.stays.repositories.StaysRepository

class GetStaysByLocationUseCase(private val repository: StaysRepository) {
    operator fun invoke(locationId: String): NetworkResult<List<Stay>> {
        return repository.getStaysByLocation(locationId)
    }
}