package com.imperatorofdwelling.android.data.net

import com.imperatorofdwelling.android.data.entities.ServerDataStaysAnswer
import retrofit2.Call
import retrofit2.http.GET

interface Stay{
    @GET("api/v1/stays")
    fun getStays(): Call<ServerDataStaysAnswer>
}