package com.imperatorofdwelling.android.presentation.ui.utils

object ErrorManager {
    fun extractErrorMessage(jsonString: String): String? {
        val regex = Regex(
            """error=(.+): (.+)"""")
        val matchResult = regex.find(jsonString)

        return matchResult?.groupValues?.get(2)
    }
}