package com.imperatorofdwelling.android.domain.cities.entities

data class City(
    val id: Int = UNKNOWN_ID,
    val name: String,
    val population: Float
) {
    companion object{
        const val UNKNOWN_ID = -1
    }
}
