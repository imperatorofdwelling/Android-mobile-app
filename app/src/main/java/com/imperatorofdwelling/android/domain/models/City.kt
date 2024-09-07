package com.imperatorofdwelling.android.domain.models

import com.google.gson.annotations.SerializedName

data class City(
    val id: Int = UNKNOWN_ID,
    @SerializedName("city") val name: String,
    val isSelected: Boolean = false
) {
    companion object{
        const val UNKNOWN_ID = -1
    }
}
