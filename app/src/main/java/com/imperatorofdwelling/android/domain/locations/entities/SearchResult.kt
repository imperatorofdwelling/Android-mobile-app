package com.imperatorofdwelling.android.domain.locations.entities


data class SearchResult(
    val id: Long,
    val lat: Double,
    val lon: Double,
    val name: String,
    val displayName: String,
    val address: Address,
) {
    override fun toString(): String {
        return displayName
    }

    fun toRussianString(): String {
        var res = ""
        this.address.city?.let {
            res += "$it, "
        }
        this.address.road?.let {
            res += "$it, "
        }
        // if road is empty, add neighbourhood
        if(this.address.road == null){
           this.address.neighbourhood?.let{
               res += "$it, "
           }
        }
        this.address.houseNumber?.let {
            res += it
        }
        return res
    }
}