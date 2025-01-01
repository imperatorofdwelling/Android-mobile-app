package com.imperatorofdwelling.android.domain.stays.entities

data class Stay(
    val id: String,
    val userId: String,
    val locationId: String,
    val name: String,
    val type: String,
    val numberOfBedrooms: Int,
    val numberOfBeds: Int,
    val numberOfBathrooms: Int,
    val guests: Int,
    val rating: Double,
    val isSmokingProhibited: Boolean,
    val square: Int,
    val street: String,
    val house: String,
    val entrance: String?,
    val floor: String?,
    val room: String?,
    val price: Int,
    val createdAt: String?,
    val updatedAt: String?,
    val isFavourites: Boolean
)
