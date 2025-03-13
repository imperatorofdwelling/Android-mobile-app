package com.imperatorofdwelling.android.domain.locations.entities

data class Address(
    val houseNumber: String?,
    val neighbourhood: String?,
    val suburb: String?,
    val cityDistrict: String?,
    val city: String?,
    val county: String?,
    val state: String?,
    val iso: String?,
    val region: String?,
    val postcode: String?,
    val country: String?,
    val countryCode: String?,
    val road: String?
){
    override fun toString(): String {
        return "$region, $city, $neighbourhood, $houseNumber"
    }
}