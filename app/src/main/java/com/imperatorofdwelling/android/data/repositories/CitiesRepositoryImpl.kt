package com.imperatorofdwelling.android.data.repositories

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.imperatorofdwelling.android.data.entities.CityEntity
import com.imperatorofdwelling.android.data.entities.mapper.CityEntityDataMapper
import com.imperatorofdwelling.android.data.entities.mapper.CityEntityDomainMapper
import com.imperatorofdwelling.android.data.local.preferences.SharedPreferencesDataSource
import com.imperatorofdwelling.android.domain.cities.entities.City
import com.imperatorofdwelling.android.domain.cities.repositories.CitiesRepository
import java.io.IOException
import javax.inject.Inject

class CitiesRepositoryImpl @Inject constructor(
    private val context: Context,
    private val sharedPreferencesDataSource: SharedPreferencesDataSource
) : CitiesRepository {


    private companion object {
        private const val DEFAULT_CITY = "default_city"
    }

    private val cities: MutableList<CityEntity> = loadCities()
    private var defaultCity: CityEntity?

    init {
        val gson = Gson()
        val json = sharedPreferencesDataSource.getString(DEFAULT_CITY)
        defaultCity = if (json != "") {
            gson.fromJson(json, CityEntity::class.java)
        } else null
    }

    override fun getCities(name: String): List<City> {
        if (name.isBlank()) return emptyList()
        val result = cities.filter { it.name.contains(name, ignoreCase = true) }
        return CityEntityDataMapper.transform(result)
    }

    private fun loadCities(): MutableList<CityEntity> {
        val gson = Gson()
        val json = loadJSONFromAsset()
        return if (json != null) {
            val cityListType = object : TypeToken<MutableList<CityEntity>>() {}.type
            gson.fromJson(json, cityListType)
        } else {
            mutableListOf()
        }
    }

    override fun setDefaultCity(city: City) {
        val cityEntity = CityEntityDomainMapper.transform(city)
        val gson = Gson()
        val serializableCity = gson.toJson(cityEntity)
        sharedPreferencesDataSource.putString(DEFAULT_CITY, serializableCity)
        defaultCity = cityEntity
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


    override fun getDefaultCity(): City? {
        return CityEntityDataMapper.transform(defaultCity)
    }
}