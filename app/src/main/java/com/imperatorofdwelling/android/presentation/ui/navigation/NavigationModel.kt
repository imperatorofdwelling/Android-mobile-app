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
            val res = getUserRoleUseCase()
            _state.update { it.copy(userRole = res) }
        }
    }

    fun onSetVisible(boolean: Boolean){
        _state.update {
            it.copy(showBottomNavigation = boolean)
        }
    }

    data class State(
        val showBottomNavigation: Boolean = true,
        val userRole: String = "Tenant"
    )
}
