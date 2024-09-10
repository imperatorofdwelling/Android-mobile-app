package com.imperatorofdwelling.android.domain.repositories

import com.imperatorofdwelling.android.domain.entities.City

interface CitiesRepository {
    fun getCities(name: String): List<City>
    fun setDefaultCity(city: City)
    fun getDefaultCity(): City?
}