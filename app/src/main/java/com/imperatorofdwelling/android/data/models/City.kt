package com.imperatorofdwelling.android.data.models

data class City(
    val id: Int = UNKNOWN_ID,
    val name: String,
    val isSelected: Boolean
) {
    companion object{
        const val UNKNOWN_ID = -1
    }
}