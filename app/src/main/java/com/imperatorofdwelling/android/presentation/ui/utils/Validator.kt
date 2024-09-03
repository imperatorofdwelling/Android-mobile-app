package com.imperatorofdwelling.android.presentation.ui.utils

object Validator {
    fun isValidUserName(name: String): Boolean{
        return name.matches(Regex("^[0-9А-Яа-яA-Za-z]+$"))
    }
    fun isValidEmail(email: String): Boolean{
        return email.matches(
            Regex("([a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.[a-zA-Z0-9_-]+)")
        )
    }

}