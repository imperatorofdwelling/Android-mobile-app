package com.imperatorofdwelling.android.data.net.nominatim

import com.imperatorofdwelling.android.data.entities.location.NominationSearchResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface Address {
    @GET("search")
    fun getAddress(
        @Query("q") q: String,
        @Query("format") format: String = "json",
        @Query("polygon_kml") polygon_kml: String = "1",
        @Query("addressdetails") addressdetails: String = "1",
        @Header("User-Agent") header: String = "DwellingApp/0.1",
    ): Call<List<NominationSearchResult>>
}