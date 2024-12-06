package com.imperatorofdwelling.android.presentation.entities

data class Dwelling (
    val id: String,
    val userId: String,
    val name: String,
    val locationId: String,
    val type: String,
    val isSmokingProhibited: Boolean,
    val square: Int,
    val street: String,
    val house: String,
    val entrance: String,
    val floor: String,
    val room: String,
    val createdAt: String,
    val updatedAt: String,
    val numberOfBedrooms: Int,
    val numberOfBeds: Int,
    val numberOfBathrooms: Int,
    val guests: Int,
    val address: String,
    val price: Price,
    val mark: Double,
    val isLiked: Boolean = false,
    var imageUrl: String? = null,
    val imageRes: Int? = null
)

