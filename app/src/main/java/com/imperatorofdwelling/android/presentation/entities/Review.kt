package com.imperatorofdwelling.android.presentation.entities

data class Review(
    val user: User,
    val mark: Double,
    val description: String?
)
