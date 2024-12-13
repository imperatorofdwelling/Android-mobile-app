package com.imperatorofdwelling.android.domain

sealed interface NetworkResult<out T: Any> {

    data class Success<T: Any>(val value: T): NetworkResult<T>

    data class Error(val errorMessage: String): NetworkResult<Nothing>

}