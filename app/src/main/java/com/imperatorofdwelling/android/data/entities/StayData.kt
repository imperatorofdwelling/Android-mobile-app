package com.imperatorofdwelling.android.data.entities

import com.google.gson.annotations.SerializedName

data class StayData(
    @SerializedName("id")
    val id: String,
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("location_id")
    val locationId: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("number_of_bedrooms")
    val numberOfBedrooms: Int,
    @SerializedName("number_of_beds")
    val numberOfBeds: Int,
    @SerializedName("number_of_bathrooms")
    val numberOfBathrooms: Int,
    @SerializedName("guests")
    val guests: Int,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("is_smoking_prohibited")
    val isSmokingProhibited: Boolean,
    @SerializedName("square")
    val square: Int,
    @SerializedName("street")
    val street: String,
    @SerializedName("house")
    val house: String,
    @SerializedName("entrance")
    val entrance: String,
    @SerializedName("floor")
    val floor: String,
    @SerializedName("room")
    val room: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String
)