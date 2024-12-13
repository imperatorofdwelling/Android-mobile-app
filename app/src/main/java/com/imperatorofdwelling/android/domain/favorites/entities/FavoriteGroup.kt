package com.imperatorofdwelling.android.domain.favorites.entities

import com.imperatorofdwelling.android.domain.locations.entities.City
import com.imperatorofdwelling.android.presentation.entities.Dwelling

data class FavoriteGroup (
    val city: City,
    val dwellings: List<Dwelling>
)