package com.imperatorofdwelling.android.domain.stays.usecases

import com.imperatorofdwelling.android.domain.NetworkResult
import com.imperatorofdwelling.android.domain.stays.repositories.StaysRepository

class GetAllAmenitiesUseCase(private val repository: StaysRepository) {
    operator fun invoke(): NetworkResult<List<String>> {
        return repository.getAllAmenities()
    }
}