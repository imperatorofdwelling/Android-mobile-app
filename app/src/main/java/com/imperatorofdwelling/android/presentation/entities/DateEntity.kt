package com.imperatorofdwelling.android.presentation.entities

data class DateEntity(
    val day: Int,
    val month: Int,
    val year: Int
) {
    operator fun compareTo(other: DateEntity): Int {
        return if (this.year != other.year) {
            this.year.compareTo(other.year)
        } else if (this.month != other.month) {
            this.month.compareTo(other.month)
        } else {
            this.day.compareTo(other.day)
        }
    }

    override fun toString(): String {
        return "$day"
    }

    fun toPresentationString(): String {
        return "${day.toString().padStart(2, '0')}.${month.toString().padStart(2, '0')}.$year"
    }
}