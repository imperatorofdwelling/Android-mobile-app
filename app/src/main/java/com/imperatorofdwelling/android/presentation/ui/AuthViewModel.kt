package com.imperatorofdwelling.android.presentation.ui

import androidx.lifecycle.viewModelScope
import com.imperatorofdwelling.android.domain.user.usecases.IsRegisteredUseCase
import com.imperatorofdwelling.android.presentation.ui.common.BaseViewModel
import com.imperatorofdwelling.android.presentation.ui.utils.LCE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel(
    val isRegisteredUseCase: IsRegisteredUseCase
): BaseViewModel<AuthViewModel.State>(State()) {

    init{
        initIsSkipRegistrationState()
    }

    private fun initIsSkipRegistrationState() {
        _lce.update{ LCE.Loading }
        var res = false
        viewModelScope.launch(Dispatchers.IO) {
            res = isRegisteredUseCase()
        }.invokeOnCompletion {
            _state.update{
                it.copy(isAuthSkip = res)
            }
            _lce.update{
                LCE.Idle
            }
        }
    }

    data class State(
        val isAuthSkip: Boolean = false
    )
}