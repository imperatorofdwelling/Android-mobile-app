package com.imperatorofdwelling.android.data.net


import com.imperatorofdwelling.android.data.entities.ServerDataAnswer
import com.imperatorofdwelling.android.data.entities.StayData
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface Favourites {
    @POST("api/v1/favourites/{stay_id}")
    fun addToFavourites(
        @Path("stay_id") stayId: String,
        @Header("Cookie") cookies: String
    ): Call<ServerDataAnswer<Unit>>

    @DELETE("api/v1/favourites/{stay_id}")
    fun deleteFavourites(
        @Path("stay_id") stayId: String,
        @Header("Cookie") cookies: String
    ): Call<ServerDataAnswer<Unit>>

    @GET("api/v1/favourites/")
    fun getAllFavourites(
        @Header("Cookie") cookies: String
    ): Call<ServerDataAnswer<Map<String, List<StayData>>>>


}