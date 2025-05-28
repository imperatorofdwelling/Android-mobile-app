package com.imperatorofdwelling.android.presentation.ui.landlord.my_objects

import com.imperatorofdwelling.android.domain.stays.entities.Stay
import com.imperatorofdwelling.android.presentation.ui.common.BaseViewModel
import kotlinx.coroutines.flow.update

class MyObjectsViewModel : BaseViewModel<MyObjectsViewModel.State>(State()) {

    init {
        initState()
    }

    private fun initState(){

    }

    fun onModeChanged(
        newValue: String
    ){
        _state.update { it.copy(selectedMode = newValue) }
    }

    data class State(
        val myObjectsList: List<Stay> = listOf(),
        val selectedMode: String = ""
    )
}