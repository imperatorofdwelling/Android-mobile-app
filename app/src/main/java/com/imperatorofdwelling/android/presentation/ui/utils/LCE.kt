package com.imperatorofdwelling.android.presentation.ui.utils

sealed interface LCE {
    data object Idle : LCE
    data object Loading : LCE
    data class Error(val throwable: Throwable) : LCE
}

fun LCE.isLoading() = this is LCE.Loading

fun LCE.isError() = this is LCE.Error