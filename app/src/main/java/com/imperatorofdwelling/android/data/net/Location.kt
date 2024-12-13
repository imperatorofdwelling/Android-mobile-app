package com.imperatorofdwelling.android.data.net

import com.imperatorofdwelling.android.data.entities.location.LocationWrapper
import retrofit2.Call
import retrofit2.http.GET

interface Location {
    @GET("api/v1/locations")
    fun getAllLocations(): Call<LocationWrapper>
}