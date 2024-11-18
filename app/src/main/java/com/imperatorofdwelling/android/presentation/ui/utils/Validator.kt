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

}