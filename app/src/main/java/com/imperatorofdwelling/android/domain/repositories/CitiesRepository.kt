package com.imperatorofdwelling.android.domain.repositories

import com.imperatorofdwelling.android.domain.models.City

interface CitiesRepository {
    fun getCities(name: String?): List<City>
}