package com.imperatorofdwelling.android.data.entities

import com.google.gson.annotations.SerializedName

data class CityEntity(
    val id: Int = UNKNOWN_ID,
    @SerializedName("city") val name: String,
    @SerializedName("population") val population: Float
) {
    companion object {
        const val UNKNOWN_ID = -1
    }
}
