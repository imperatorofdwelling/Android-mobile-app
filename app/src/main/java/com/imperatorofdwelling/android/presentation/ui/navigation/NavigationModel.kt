package com.imperatorofdwelling.android.presentation.ui.navigation

import com.imperatorofdwelling.android.presentation.ui.common.BaseViewModel
import kotlinx.coroutines.flow.update

class NavigationModel(
): BaseViewModel<NavigationModel.State>(State()) {

    fun onSetVisible(boolean: Boolean){
        _state.update {
            it.copy(showBottomNavigation = boolean)
        }
    }

    data class State(
        val showBottomNavigation: Boolean = true
    )
}
