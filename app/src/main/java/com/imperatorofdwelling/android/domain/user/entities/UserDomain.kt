package com.imperatorofdwelling.android.domain.user.entities

data class UserDomain (
    val avatar: List<Int>?,
    val birthDate: BirthDateDomain?,
    val city: String,
    val country: String,
    val createdAt: String,
    val email: String,
    val gender: String,
    val id: String,
    val name: String,
    val national: String,
    val phone: String,
    val updatedAt: String
)

data class BirthDateDomain(
    val time: String?,
    val valid: Boolean?
)