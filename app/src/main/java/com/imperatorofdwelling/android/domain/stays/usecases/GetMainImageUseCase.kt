package com.imperatorofdwelling.android.domain.stays.usecases

import com.imperatorofdwelling.android.domain.NetworkResult
import com.imperatorofdwelling.android.domain.stays.repositories.StaysRepository

class GetMainImageUseCase(private val repository: StaysRepository) {
    operator fun invoke(stayId: String): NetworkResult<String> {
        return repository.getMainImage(stayId)
    }
}