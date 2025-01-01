package com.imperatorofdwelling.android.presentation.ui.user_profile

import androidx.lifecycle.viewModelScope
import com.imperatorofdwelling.android.domain.user.usecases.GetTokenUseCase
import com.imperatorofdwelling.android.domain.user.usecases.IsRegisteredUseCase
import com.imperatorofdwelling.android.presentation.ui.common.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserProfileViewModel(
    private val isRegisteredUseCase: IsRegisteredUseCase,
    private val getTokenUserCase: GetTokenUseCase
): BaseViewModel<UserProfileViewModel.State>(State()) {

    init{
        initJwtTocken()
    }

    private fun initJwtTocken(){
        var res = ""
        viewModelScope.launch(Dispatchers.IO) {
            res = getTokenUserCase()
        }.invokeOnCompletion {
            _state.update {
                it.copy(jwt = res)
            }
        }
    }

    data class State(
        val jwt: String = "empty"
    )
}