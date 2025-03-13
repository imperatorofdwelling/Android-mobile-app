package com.imperatorofdwelling.android.domain.locations.repositories

import com.imperatorofdwelling.android.domain.NetworkResult
import com.imperatorofdwelling.android.domain.locations.entities.City
import com.imperatorofdwelling.android.domain.locations.entities.SearchResult

interface LocationRepository {
    fun getCities(name: String): NetworkResult<List<City>>
    fun setDefaultCity(city: City)
    fun getDefaultCity(): City?
    fun getAddress(q: String): NetworkResult<List<SearchResult>>
}