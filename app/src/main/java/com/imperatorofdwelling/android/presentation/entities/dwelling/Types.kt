package com.imperatorofdwelling.android.presentation.entities.dwelling

import com.imperatorofdwelling.android.R

sealed class TypeOfDwelling {
    abstract val iconDrawableId: Int
    abstract val nameStringId: Int
}


data object House : TypeOfDwelling() {
    override val iconDrawableId: Int = R.drawable.home
    override val nameStringId: Int = R.string.house

    override fun toString(): String {
        return "house"
    }
}

data object Apartment : TypeOfDwelling() {
    override val iconDrawableId: Int = R.drawable.apart
    override val nameStringId: Int = R.string.apartment

    override fun toString(): String {
        return "apartment"
    }
}

data object Hotel : TypeOfDwelling() {
    override val iconDrawableId: Int = R.drawable.hotel
    override val nameStringId: Int = R.string.hotel

    override fun toString(): String {
        return "hotel"
    }
}