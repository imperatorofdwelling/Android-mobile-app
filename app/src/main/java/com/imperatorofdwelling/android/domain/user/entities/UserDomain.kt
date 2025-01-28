package com.imperatorofdwelling.android.domain.user.entities

data class UserDomain (
    val avatar: List<Int>? = emptyList(),
    val birthDate: BirthDateDomain?,
    val city: String? = null,
    val country: String? = null,
    val createdAt: String? = null,
    val email: String,
    val gender: String? = null,
    val id: String,
    val name: String,
    val national: String? = null,
    val phone: String?,
    val updatedAt: String? = null
)

data class BirthDateDomain(
    val time: String?,
    val valid: Boolean?
)