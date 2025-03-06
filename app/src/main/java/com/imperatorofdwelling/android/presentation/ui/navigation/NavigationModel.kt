package com.imperatorofdwelling.android.presentation.ui.navigation

import androidx.lifecycle.viewModelScope
import com.imperatorofdwelling.android.domain.user.usecases.GetUserRoleUseCase
import com.imperatorofdwelling.android.presentation.ui.common.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NavigationModel(
    private val getUserRoleUseCase: GetUserRoleUseCase
): BaseViewModel<NavigationModel.State>(State()) {

    init{
        updateUserRole()
    }

    fun updateUserRole() {
        viewModelScope.launch (Dispatchers.IO){
            getUserRoleUseCase().collect{ role ->
                _state.update { it.copy(userRole = role) }
            }
        }
    }

    fun onSetVisible(boolean: Boolean){
        _state.update {
            it.copy(showBottomNavigation = boolean)
        }
    }

    data class State(
        val showBottomNavigation: Boolean = true,
        val userRole: Int = 0
    )
}
