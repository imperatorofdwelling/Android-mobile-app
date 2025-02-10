package com.imperatorofdwelling.android.domain.reviews.entities

import com.imperatorofdwelling.android.presentation.entities.User

data class Review(
    val user: User,
    val mark: Double,
    val description: String?
)
