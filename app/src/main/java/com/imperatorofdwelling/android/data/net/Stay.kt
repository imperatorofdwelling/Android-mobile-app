package com.imperatorofdwelling.android.data.net

import com.imperatorofdwelling.android.data.entities.ImageData
import com.imperatorofdwelling.android.data.entities.ServerDataAnswer
import com.imperatorofdwelling.android.data.entities.ServerDataListAnswer
import com.imperatorofdwelling.android.data.entities.StayData
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface Stay {
    @GET("api/v1/stays")
    fun getStays(): Call<ServerDataListAnswer<StayData>>

    @GET("api/v1/stays/location/{location_id}")
    fun getStaysByLocation(@Path("location_id") locationId: String): Call<ServerDataListAnswer<StayData>>

    @GET("api/v1/stays/images/main/{id}")
    fun getMainImage(@Path("id") id: String): Call<ServerDataAnswer<ImageData>>

    @GET("api/v1/stays/{id}")
    fun getStayById(@Path("id") id: String): Call<ServerDataAnswer<StayData>>

    @GET("api/v1/stays/filtration/amenities")
    fun getAmenities(): Call<ServerDataAnswer<List<String>>>

    @POST("api/v1/stays")
    fun createStay(
        @Body
        stayData: StayData,
        @Header("Cookie") cookies: String
    ): Call<ServerDataAnswer<String>>

    @POST("api/v1/stays/images/main/")
    fun createMainImage(
        @Header("Cookie") cookies: String,
        @Part image: MultipartBody.Part,
        @Part stayId: MultipartBody.Part,
    ): Call<ServerDataAnswer<String>>

}