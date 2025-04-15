package com.imperatorofdwelling.android.domain.locations.usecases

import com.imperatorofdwelling.android.domain.locations.entities.SearchResult
import com.imperatorofdwelling.android.domain.locations.repositories.LocationRepository

class SaveAddressUseCase(private val repository: LocationRepository) {
    operator fun invoke(address: SearchResult) {
        repository.saveAddress(address)
    }
}