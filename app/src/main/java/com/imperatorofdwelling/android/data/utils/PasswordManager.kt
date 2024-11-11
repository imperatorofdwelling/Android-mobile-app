package com.imperatorofdwelling.android.data.utils

import java.security.MessageDigest

object PasswordManager {
    private const val ALGORITHM_HASHING = "SHA-256"
    fun sha256(password: String): String {
        val digest = MessageDigest.getInstance(ALGORITHM_HASHING)
        val hash = digest.digest(password.toByteArray())
        return hash.joinToString("") { "%02x".format(it) }
    }
}