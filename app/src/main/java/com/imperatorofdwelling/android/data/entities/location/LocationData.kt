package com.imperatorofdwelling.android.data.entities.location

import com.google.gson.annotations.SerializedName

data class LocationData(
    @SerializedName("city")
    val city: String,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("federal_district")
    val federalDistrict: String?,
    @SerializedName("fias_id")
    val fiasId: String?,
    @SerializedName("id")
    val id: String,
    @SerializedName("kladr_id")
    val kladrId: String?,
    @SerializedName("lat")
    val lat: String?,
    @SerializedName("lon")
    val lon: String?,
    @SerializedName("okato")
    val okato: String?,
    @SerializedName("oktmo")
    val oktmo: String?,
    @SerializedName("population")
    val population: Float?,
    @SerializedName("region_iso_code")
    val regionIsoCode: String?,
    @SerializedName("region_name")
    val regionName: String?,
    @SerializedName("updated_at")
    val updatedAt: String?
)
