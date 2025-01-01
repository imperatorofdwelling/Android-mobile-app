package com.imperatorofdwelling.android.data.net

import com.imperatorofdwelling.android.data.entities.ServerDataAnswer
import com.imperatorofdwelling.android.domain.auth.entities.LoginData
import com.imperatorofdwelling.android.domain.auth.entities.RegistrationData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface User {
    @POST("api/v1/registration")
    fun registration(
        @Body body: RegistrationData
    ) : Call<ServerDataAnswer<String>>

    @POST("api/v1/login")
    fun login(
        @Body body: LoginData
    ): Call<ServerDataAnswer<String>>

}