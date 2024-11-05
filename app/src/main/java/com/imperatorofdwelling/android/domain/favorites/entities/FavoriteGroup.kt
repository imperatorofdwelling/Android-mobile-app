package com.imperatorofdwelling.android.domain.favorites.entities

import com.imperatorofdwelling.android.domain.cities.entities.City
import com.imperatorofdwelling.android.presentation.entities.Dwelling

data class FavoriteGroup (
    val city: City,
    val dwellings: List<Dwelling>
)