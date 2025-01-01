package com.imperatorofdwelling.android.domain.locations.repositories

import com.imperatorofdwelling.android.domain.NetworkResult
import com.imperatorofdwelling.android.domain.locations.entities.City

interface LocationRepository {
    fun getCities(name: String): NetworkResult<List<City>>
    fun setDefaultCity(city: City)
    fun getDefaultCity(): City?
}