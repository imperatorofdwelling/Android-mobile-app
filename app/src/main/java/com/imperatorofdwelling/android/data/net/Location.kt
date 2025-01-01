package com.imperatorofdwelling.android.data.net

import com.imperatorofdwelling.android.data.entities.ServerDataListAnswer
import com.imperatorofdwelling.android.data.entities.location.LocationData
import retrofit2.Call
import retrofit2.http.GET

interface Location {
    @GET("api/v1/locations")
    fun getAllLocations(): Call<ServerDataListAnswer<LocationData>>
}