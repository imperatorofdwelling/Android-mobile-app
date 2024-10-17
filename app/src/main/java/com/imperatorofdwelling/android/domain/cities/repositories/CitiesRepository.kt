package com.imperatorofdwelling.android.domain.cities.repositories

import com.imperatorofdwelling.android.domain.cities.entities.City

interface CitiesRepository {
    fun getCities(name: String): List<City>
    fun setDefaultCity(city: City)
    fun getDefaultCity(): City?
}