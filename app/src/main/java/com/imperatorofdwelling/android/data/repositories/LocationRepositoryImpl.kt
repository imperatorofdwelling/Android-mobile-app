package com.imperatorofdwelling.android.data.repositories

import com.google.gson.Gson
import com.imperatorofdwelling.android.data.entities.location.LocationData
import com.imperatorofdwelling.android.data.entities.location.toSearchResult
import com.imperatorofdwelling.android.data.entities.mapper.LocationDataMapper
import com.imperatorofdwelling.android.data.entities.mapper.LocationDomainMapper
import com.imperatorofdwelling.android.data.local.preferences.SharedPreferencesDataSource
import com.imperatorofdwelling.android.data.net.ApiClient
import com.imperatorofdwelling.android.data.net.nominatim.ApiClient as NominatimApiClient
import com.imperatorofdwelling.android.domain.NetworkResult
import com.imperatorofdwelling.android.domain.locations.entities.City
import com.imperatorofdwelling.android.domain.locations.entities.SearchResult
import com.imperatorofdwelling.android.domain.locations.repositories.LocationRepository
import java.io.IOException

class LocationRepositoryImpl(
    private val sharedPreferencesDataSource: SharedPreferencesDataSource
) : LocationRepository {


    private companion object {
        private const val DEFAULT_CITY = "default_city"
    }

    private var cities: List<LocationData> = emptyList()
    private var defaultCity: LocationData? = null

    init {
        loadDefaultCity()
    }

    private fun loadDefaultCity() {
        val gson = Gson()
        val json = sharedPreferencesDataSource.getString(DEFAULT_CITY)
        defaultCity = if (json != "") {
            gson.fromJson(json, LocationData::class.java)
        } else null
    }

    override fun getCities(name: String): NetworkResult<List<City>> {
        if (cities.isEmpty()) {
            cities = loadCities()
        }
        if (name.isBlank()) return NetworkResult.Success<List<City>>(emptyList())
        val result = cities.filter { it.city.contains(name, ignoreCase = true) }
        return NetworkResult.Success(LocationDomainMapper.transform(result))
    }

    private fun loadCities(): List<LocationData> {
        val result = ApiClient
            .getLocation()
            .getAllLocations()
            .execute()


        if (result.isSuccessful) {
            return result.body()?.data ?: emptyList()
        }
        throw IOException("Server Error + ${result.errorBody()}")
    }

    override fun setDefaultCity(city: City) {
        val cityEntity = LocationDataMapper.transform(city)
        val gson = Gson()
        val serializableCity = gson.toJson(cityEntity)
        sharedPreferencesDataSource.putString(DEFAULT_CITY, serializableCity)
        defaultCity = cityEntity
    }

    override fun getDefaultCity(): City? {
        return LocationDomainMapper.transform(defaultCity)
    }

    override fun getAddress(q: String): NetworkResult<List<SearchResult>> {
        val result = NominatimApiClient
            .getAddress()
            .getAddress(q = q)
            .execute()
        if (result.isSuccessful) {
            return NetworkResult.Success(
                result.body()?.map { it.toSearchResult() } ?: throw IOException(
                    "Server Error + ${result.errorBody()}"
                )
            )
        }
        return NetworkResult.Error("${result.errorBody()?.string()}, $result")
    }
}