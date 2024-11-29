package com.imperatorofdwelling.android.presentation.entities

import com.imperatorofdwelling.android.presentation.entities.dwelling.Amenity
import com.imperatorofdwelling.android.presentation.entities.dwelling.BedsAmenity
import com.imperatorofdwelling.android.presentation.entities.dwelling.RoomsAmenity
import com.imperatorofdwelling.android.presentation.entities.dwelling.WiFiAmenity


val dwellingMock = Dwelling(
    "Москва",
    "Бул. Покровский, 11, стр. 10",
    Price(Ruble(), 4500, Period.Daily),
    mark = 4.5
)


val amenityListMock = listOf<Amenity>(
    RoomsAmenity(2),
    BedsAmenity(3),
    WiFiAmenity()
)