package com.imperatorofdwelling.android.data.entities

import com.google.gson.annotations.SerializedName

data class ImageData(
    @SerializedName("id")
    val id: String,
    @SerializedName("stay_id")
    val stayId: String,
    @SerializedName("image_name")
    val imageName: String,
    @SerializedName("is_main")
    val isMain: Boolean,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String
)
