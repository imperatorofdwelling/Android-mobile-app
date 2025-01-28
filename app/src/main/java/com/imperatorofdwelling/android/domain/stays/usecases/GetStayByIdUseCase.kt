package com.imperatorofdwelling.android.domain.stays.usecases

import com.imperatorofdwelling.android.domain.stays.repositories.StaysRepository

class GetStayByIdUseCase(private val repository: StaysRepository) {
    suspend operator fun invoke(stayId: String) = repository.getStayById(stayId)
}