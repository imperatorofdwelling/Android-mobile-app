package com.imperatorofdwelling.android.data.entities

import com.google.gson.annotations.SerializedName

data class UserData(
    @SerializedName("avatar")
    val avatar: String?,

    @SerializedName("birth_date")
    val birthDate: BirthDateData?,

    @SerializedName("city")
    val city: String,

    @SerializedName("country")
    val country: String,

    @SerializedName("createdAt")
    val createdAt: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("gender")
    val gender: String,

    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("national")
    val national: String,

    @SerializedName("phone")
    val phone: String,

    @SerializedName("updatedAt")
    val updatedAt: String
)

data class BirthDateData(
    @SerializedName("time")
    val time: String?,

    @SerializedName("valid")
    val valid: Boolean?
)