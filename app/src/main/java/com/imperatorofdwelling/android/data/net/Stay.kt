package com.imperatorofdwelling.android.data.net

import com.imperatorofdwelling.android.data.entities.ImageData
import com.imperatorofdwelling.android.data.entities.ServerDataAnswer
import com.imperatorofdwelling.android.data.entities.ServerDataListAnswer
import com.imperatorofdwelling.android.data.entities.StayData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Stay {
    @GET("api/v1/stays")
    fun getStays(): Call<ServerDataListAnswer<StayData>>
    @GET("api/v1/stays/location/{location_id}")
    fun getStaysByLocation(@Path("location_id") locationId: String) : Call<ServerDataListAnswer<StayData>>
    @GET("api/v1/stays/images/main/{id}")
    fun getMainImage(@Path("id") id: String): Call<ServerDataAnswer<ImageData>>
    @GET("api/v1/stays/{id}")
    fun getStayById(@Path("id") id: String): Call<ServerDataAnswer<StayData>>
}