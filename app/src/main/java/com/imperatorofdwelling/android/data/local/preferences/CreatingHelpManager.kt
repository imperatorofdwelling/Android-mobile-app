package com.imperatorofdwelling.android.data.local.preferences

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CreatingHelpManager(
    private val sharedPreferencesDataSource: SharedPreferencesDataSource
) {
    private val _value =
        MutableStateFlow(getValue())

    val value: StateFlow<Boolean> = _value

    private fun getValue() = sharedPreferencesDataSource.getBoolean(KEY, true)

    fun updateValue(newValue: Boolean){
        _value.value = newValue
    }

    companion object KeyHandler {
        const val KEY = "creating_help"
    }
}