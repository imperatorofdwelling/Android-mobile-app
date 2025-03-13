package com.imperatorofdwelling.android.data.entities.location

import com.google.gson.annotations.SerializedName
import com.imperatorofdwelling.android.domain.locations.entities.Address as DomainAddress

data class Address(
    val house_number: String?,
    val neighbourhood: String?,
    val suburb: String?,
    val city_district: String?,
    val city: String?,
    val county: String?,
    val state: String?,
    @SerializedName("ISO3166-2-lvl4")
    val iso3166_2_lvl4: String?,
    val region: String?,
    val postcode: String?,
    val country: String?,
    val country_code: String?,
    val road: String?
)

fun Address.toDomainAddress(): DomainAddress {
    return DomainAddress(
        houseNumber = this.house_number,
        neighbourhood = this.neighbourhood,
        suburb = this.suburb,
        cityDistrict = this.city_district,
        city = this.city,
        county = this.county,
        state = this.state,
        iso = this.iso3166_2_lvl4,
        region = this.region,
        postcode = this.postcode,
        country = this.country,
        countryCode = this.country_code,
        road = this.road
    )
}