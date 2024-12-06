package com.imperatorofdwelling.android.domain.stays.usecases

import com.imperatorofdwelling.android.domain.auth.entities.NetworkResult
import com.imperatorofdwelling.android.domain.stays.entities.Stay
import com.imperatorofdwelling.android.domain.stays.repositories.StaysRepository

class GetAllStaysUseCase(private val repository: StaysRepository) {
    operator fun invoke(): NetworkResult<List<Stay>> {
        return repository.getAllStays()
    }
}