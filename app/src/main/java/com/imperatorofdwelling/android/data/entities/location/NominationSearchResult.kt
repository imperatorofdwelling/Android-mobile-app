package com.imperatorofdwelling.android.data.entities.location

import com.imperatorofdwelling.android.domain.locations.entities.SearchResult

data class NominationSearchResult
(
    val place_id: Long,
    val licence: String,
    val osm_type: String,
    val osm_id: Long,
    val lat: String,
    val lon: String,
    val `class`: String,
    val type: String,
    val place_rank: Int,
    val importance: Double,
    val addresstype: String,
    val name: String,
    val display_name: String,
    val address: Address,
    val boundingbox: List<String>,
    val geokml: String
)

fun NominationSearchResult.toSearchResult(): SearchResult {
    return SearchResult(
        id = this.place_id,
        lat = this.lat.toDouble(),
        lon = this.lon.toDouble(),
        displayName = this.display_name,
        address = this.address.toDomainAddress(),
        name = this.name
    )
}