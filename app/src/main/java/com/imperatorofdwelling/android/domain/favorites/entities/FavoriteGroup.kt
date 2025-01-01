package com.imperatorofdwelling.android.domain.favorites.entities

import com.imperatorofdwelling.android.domain.locations.entities.City

data class FavoriteGroup (
    val city: City,
    val dwellings: List<Favourites>
)