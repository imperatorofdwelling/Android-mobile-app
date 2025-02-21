package com.imperatorofdwelling.android.presentation.ui.home_screen.filtration

import com.imperatorofdwelling.android.presentation.ui.common.BaseViewModel
import kotlinx.coroutines.flow.update

class FiltrationViewModel : BaseViewModel<FiltrationViewModel.State>(State()) {
    init {
        initState()
    }

    private fun initState() {
        _state.update {
            it.copy(numberOfRoomsList = listOf("1", "2", "3", "4", "5", "6+"))
        }
    }

    fun onFromValueChange(value: String) {
        _state.update {
            it.copy(fromValue = value)
        }
    }

    fun onToValueChange(value: String) {
        _state.update {
            it.copy(toValue = value)
        }
    }

    data class State(
        val numberOfRoomsList: List<String> = emptyList(),
        val numberOfRoomsSelectedList: List<String> = emptyList(),
        val fromValue: String = "",
        val toValue: String = "",
        val a: Int = 0
    )
}