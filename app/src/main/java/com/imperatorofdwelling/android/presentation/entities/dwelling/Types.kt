package com.imperatorofdwelling.android.presentation.entities.dwelling

import com.imperatorofdwelling.android.R

sealed class TypeOfDwelling {

    abstract val iconDrawableId: Int
    abstract val nameStringId: Int
}



class House(): TypeOfDwelling() {
    override val iconDrawableId: Int = R.drawable.home
    override val nameStringId: Int = R.string.house
}
class Apartment(): TypeOfDwelling() {
    override val iconDrawableId: Int = R.drawable.apart
    override val nameStringId: Int = R.string.apartment
}
class Hotel(): TypeOfDwelling() {
    override val iconDrawableId: Int = R.drawable.hotel
    override val nameStringId: Int = R.string.hotel
}