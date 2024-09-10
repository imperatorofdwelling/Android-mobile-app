package com.imperatorofdwelling.android.domain.entities

import com.google.gson.annotations.SerializedName

data class City(
    val id: Int = UNKNOWN_ID,
    @SerializedName("city") val name: String
) {
    companion object{
        const val UNKNOWN_ID = -1
    }
}
