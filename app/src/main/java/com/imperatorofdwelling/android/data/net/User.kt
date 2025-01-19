package com.imperatorofdwelling.android.data.net

import com.imperatorofdwelling.android.data.entities.ServerDataAnswer
import com.imperatorofdwelling.android.data.entities.UserData
import com.imperatorofdwelling.android.domain.auth.entities.LoginData
import com.imperatorofdwelling.android.domain.auth.entities.RegistrationData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface User {
    @POST("api/v1/registration")
    fun registration(
        @Body body: RegistrationData
    ) : Call<ServerDataAnswer<String>>

    @POST("api/v1/login")
    fun login(
        @Body body: LoginData
    ): Call<ServerDataAnswer<String>>

    @GET("api/v1/user/{user_id}")
    fun getData(
        @Path("user_id")
        userId: String
    ): Call<ServerDataAnswer<UserData>>
}