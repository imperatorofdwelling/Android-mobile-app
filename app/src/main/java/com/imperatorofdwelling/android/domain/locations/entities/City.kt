package com.imperatorofdwelling.android.domain.locations.entities


data class City(
    val city: String,
    val createdAt: String?,
    val federalDistrict: String?,
    val fiasId: String?,
    val id: String,
    val kladrId: String?,
    val lat: String?,
    val lon: String?,
    val okato: String?,
    val oktmo: String?,
    val population: Float?,
    val regionIsoCode: String?,
    val regionName: String?,
    val updatedAt: String?
)
