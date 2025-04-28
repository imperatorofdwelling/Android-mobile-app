package com.imperatorofdwelling.android.domain.stays.usecases

import com.imperatorofdwelling.android.domain.stays.entities.Stay
import com.imperatorofdwelling.android.domain.stays.repositories.StaysRepository

class CreateStayUseCase(private val staysRepository: StaysRepository) {
    operator fun invoke(stay: Stay) = staysRepository.createStay(stay)
}