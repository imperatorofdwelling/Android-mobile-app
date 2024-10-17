package com.imperatorofdwelling.android.presentation.entities

sealed class Currency {
    abstract val symbol: String
}

class Ruble : Currency(){
    override val symbol = "₽"
}
class Euro : Currency(){
    override val symbol = "€"
}
class Dollar : Currency(){
    override val symbol = "$"
}