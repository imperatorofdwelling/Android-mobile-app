package com.imperatorofdwelling.android.presentation.entities

import android.graphics.drawable.Drawable

data class Dwelling (
    val name: String,
    val address: String,
    val price: Price,
    val mark: Double,
    val imageUrl: String? = null,
    val imageRes: Drawable? = null
)