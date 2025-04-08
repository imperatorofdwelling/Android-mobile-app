package com.imperatorofdwelling.android.domain.stays.usecases

import com.imperatorofdwelling.android.domain.stays.repositories.StaysRepository

class UpdateCreatingHelpUseCase(private val repository: StaysRepository) {
    operator fun invoke(value: Boolean){
        repository.updateCreatingHelp(value)
    }
}