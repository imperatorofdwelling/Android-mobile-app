package com.imperatorofdwelling.android.data.repositories

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.imperatorofdwelling.android.domain.models.City
import com.imperatorofdwelling.android.domain.repositories.CitiesRepository
import java.io.IOException

class CitiesRepositoryImpl(private val context: Context) : CitiesRepository {

    private val cities = loadCities()

    private fun loadCities(): List<City>? {
        val gson = Gson()
        val json = loadJSONFromAsset()
        return if (json != null) {
            val cityListType = object : TypeToken<List<City>>() {}.type
            gson.fromJson(json, cityListType)
        } else {
            emptyList()
        }
    }

    private fun loadJSONFromAsset(): String? {
        return try {
            val inputStream = context.assets.open("cities.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charsets.UTF_8)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    override fun getCities(name: String?): List<City> {

        if (name == null) {
            return cities?: emptyList()
        }

        val result = cities?.filter { it.name.contains(name, ignoreCase = true) }
        return result?: emptyList()
    }
}