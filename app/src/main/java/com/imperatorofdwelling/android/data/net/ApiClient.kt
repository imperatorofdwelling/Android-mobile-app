package com.imperatorofdwelling.android.data.net

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    private const val BASE_URL = "http://81.200.153.83/"
    const val BASE_FILE_URL = "http://81.200.153.83/api/v1/file/"
    private val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    fun getUser(): User {
        return retrofit.create(User::class.java)
    }

    fun getStay(): Stay{
        return retrofit.create(Stay::class.java)
    }

    fun getLocation() : Location{
        return retrofit.create(Location::class.java)
    }

    fun getFavourites(): Favourites{
        return retrofit.create(Favourites::class.java)
    }
}