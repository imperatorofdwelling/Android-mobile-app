package com.imperatorofdwelling.android.presentation.entities

data class Dwelling (
    val name: String,
    val address: String,
    val price: Price,
    val mark: Double,
    val isLiked: Boolean = false,
    val imageUrl: String? = null,
    val imageRes: Int? = null
)