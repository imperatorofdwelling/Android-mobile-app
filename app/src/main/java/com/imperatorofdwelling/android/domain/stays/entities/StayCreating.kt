package com.imperatorofdwelling.android.domain.stays.entities

data class StayCreating (
    val entrance: String = "",
    val floor: String = "",
    val room: String = "",
    val price: Int,
    val isSmokingProhibited: Boolean,
    val numberOfBedrooms: Int,
    val numberOfBeds: Int,
    val amenities: Map<String, Boolean>,
    val numberOfBathrooms: Int,
)