package com.imperatorofdwelling.android.presentation.ui.utils

object Validator {
    private const val MINIMUM_LENGTH_USER_NAME = 3
    fun isValidLengthUserName(name: String): Boolean{
        return name.length >= MINIMUM_LENGTH_USER_NAME
    }

    fun isValidUserName(name: String): Boolean{
        return name.matches(Regex("^[0-9А-Яа-яA-Za-z]+$"))
    }
    fun isValidEmail(email: String): Boolean{
        return email.matches(
            Regex("([a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.[a-zA-Z0-9_-]+)")
        )
    }

    fun isValidPhone(phone: String): Boolean{
        return phone.length > 3 && phone.matches(
            Regex("^[+]?\\d*$")
        )
    }

    fun isValidDate(date: String): Boolean {
        val regex = Regex("^\\d{2}\\d{2}\\d{4}$")
        if (!date.matches(regex)) return false

        val day = date.take(2)
        val month = date.drop(2).take(2)
        val year = date.drop(4).take(4)
        return try {
            val dayInt = day.toInt()
            val monthInt = month.toInt()
            val yearInt = year.toInt()

            if (yearInt !in 1900..2100) return false

            if (monthInt !in 1..12) return false

            when (monthInt) {
                4, 6, 9, 11 -> dayInt in 1..30
                2 -> {
                    if (isLeapYear(yearInt)) dayInt in 1..29 else dayInt in 1..28
                }
                else -> dayInt in 1..31
            }
        } catch (e: NumberFormatException) {
            false
        }
    }

    private fun isLeapYear(year: Int): Boolean {

        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
    }

}