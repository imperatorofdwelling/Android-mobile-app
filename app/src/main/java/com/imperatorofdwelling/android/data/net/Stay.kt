package com.imperatorofdwelling.android.data.net

import com.imperatorofdwelling.android.data.entities.ImageWrapper
import com.imperatorofdwelling.android.data.entities.ServerDataStaysAnswer
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Stay {
    @GET("api/v1/stays")
    fun getStays(): Call<ServerDataStaysAnswer>
    @GET("api/v1/stays/location/{location_id}")
    fun getStaysByLocation(@Path("location_id") locationId: String) : Call <ServerDataStaysAnswer>
    @GET("api/v1/stays/images/main/{id}")
    fun getMainImage(@Path("id") id: String): Call<ImageWrapper>
}