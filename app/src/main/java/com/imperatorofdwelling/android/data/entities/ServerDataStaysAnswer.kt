package com.imperatorofdwelling.android.data.entities

import com.google.gson.annotations.SerializedName

data class ServerDataStaysAnswer(
    @SerializedName("data")
    val data: List<StayData>
)
