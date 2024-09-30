package com.imperatorofdwelling.android.presentation.entities


data class Price(
    val currency: Currency,
    val amount: Int,
    val period: Period
){
    override fun toString(): String {
        val currencyChar = when(currency){
            Currency.EU -> '€'
            Currency.US -> '$'
            Currency.RUB -> '₽'
        }
        val periodStr = when(period){
            Period.Daily -> "day"
            Period.Monthly -> "month"
            Period.Hourly -> "hour"
            Period.Nightly -> "night"
        }
        return "$currencyChar$amount/$periodStr"
    }
}
