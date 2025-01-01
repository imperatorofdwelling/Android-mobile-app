package com.imperatorofdwelling.android.data.entities

import com.google.gson.annotations.SerializedName

data class FavouritesData(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("stay_id")
    val stayId: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("user_id")
    val userId: String
)
