package com.imperatorofdwelling.android.data.utils

object CookieParser {
    fun extractJwtToken(cookie: String?): String?{
        val regex = Regex("jwt-token=([^;]+)")
        val matchResult = regex.find(cookie?: "")
        return matchResult?.groupValues?.get(1)
    }
}