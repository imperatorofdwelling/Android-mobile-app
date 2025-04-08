package com.imperatorofdwelling.android.domain.stays.usecases

import com.imperatorofdwelling.android.domain.stays.repositories.StaysRepository

class GetCreatingHelpUseCase(private val repository: StaysRepository) {
    operator fun invoke() = repository.getCreatingHelp()
}