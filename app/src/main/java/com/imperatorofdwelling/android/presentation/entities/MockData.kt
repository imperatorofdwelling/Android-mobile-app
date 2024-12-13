package com.imperatorofdwelling.android.presentation.entities

import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.entities.dwelling.Amenity
import com.imperatorofdwelling.android.presentation.entities.dwelling.BedsAmenity
import com.imperatorofdwelling.android.presentation.entities.dwelling.RoomsAmenity
import com.imperatorofdwelling.android.presentation.entities.dwelling.WiFiAmenity


//val dwellingMock = Dwelling(
//    "Москва",
//    "Бул. Покровский, 11, стр. 10",
//    Price(Ruble(), 4500, Period.Daily),
//    mark = 4.5
//)


val amenityListMock = listOf<Amenity>(
    RoomsAmenity(2),
    BedsAmenity(3),
    WiFiAmenity()
)

val reviewListMock = listOf<Review>(
    Review(
        user = User(imageId = R.drawable.example_hotel_image, name = "Ivan Shinkaruk"),
        mark = 4.0,
        description = "Lorem ipsum dolor sit emet Lorem ipsum dolor sit emet Lorem ipsum dolor sit" +
                " emet Lorem ipsum dolor sit emet Lorem ipsum dolor sit emet Lorem ipsum dolor sit" +
                " emet Lorem ipsum dolor sit emet Lorem ipsum dolor sit emet Lorem ipsum dolor sit" +
                " emet"
    ),
    Review(
        user = User(imageId = R.drawable.example_hotel_image, name = "Maxim Emelev"),
        mark = 2.0,
        description = "Lorem ipsum dolor sit emet Lorem ipsum dolor sit emet Lorem ipsum dolor sit" +
                " emet Lorem ipsum dolor sit emet Lorem ipsum dolor sit emet Lorem ipsum dolor sit" +
                " emet Lorem ipsum dolor sit emet Lorem ipsum dolor sit emet Lorem ipsum dolor sit" +
                " emet"
    ),
    Review(
        user = User(imageId = R.drawable.example_hotel_image, name = "Dongak Blake"),
        mark = 5.0,
        description = "Lorem ipsum dolor sit emet Lorem ipsum dolor sit emet Lorem ipsum dolor sit" +
                " emet Lorem ipsum dolor sit emet Lorem ipsum dolor sit emet Lorem ipsum dolor sit" +
                " emet Lorem ipsum dolor sit emet Lorem ipsum dolor sit emet Lorem ipsum dolor sit" +
                " emet"
    )
)