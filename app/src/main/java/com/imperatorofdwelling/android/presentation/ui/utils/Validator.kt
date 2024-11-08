package com.imperatorofdwelling.android.presentation.ui.utils

object Validator {
    private const val MINIMUM_LENGTH_USER_NAME = 3
    fun isValidUserName(name: String): Boolean{
        if(name.length < MINIMUM_LENGTH_USER_NAME){
            return false
        }
        return name.matches(Regex("^[0-9А-Яа-яA-Za-z]+$"))
    }
    fun isValidEmail(email: String): Boolean{
        return email.matches(
            Regex("([a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.[a-zA-Z0-9_-]+)")
        )
    }

}