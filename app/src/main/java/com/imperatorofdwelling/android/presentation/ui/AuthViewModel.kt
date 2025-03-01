package com.imperatorofdwelling.android.presentation.ui

import androidx.lifecycle.viewModelScope
import com.imperatorofdwelling.android.domain.user.usecases.IsRegisteredUseCase
import com.imperatorofdwelling.android.presentation.ui.common.BaseViewModel
import com.imperatorofdwelling.android.presentation.ui.utils.LCE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel(
    val isRegisteredUseCase: IsRegisteredUseCase
) : BaseViewModel<AuthViewModel.State>(State()) {

    init {
        initIsSkipRegistrationState()
    }

    private fun initIsSkipRegistrationState() {
        _lce.update { LCE.Loading }
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val res = isRegisteredUseCase()
                _state.update {
                    it.copy(isAuthSkip = res)
                }
                _lce.update {
                    LCE.Idle
                }
            } catch (e: Exception) {
                _lce.update {
                    LCE.Error(e)
                }
            }
        }
    }

    fun tryAgainClick(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val res = isRegisteredUseCase()
                _state.update {
                    it.copy(isAuthSkip = res)
                }
                _lce.update {
                    LCE.Idle
                }
            } catch (e: Exception) {
                _lce.update {
                    LCE.Error(e)
                }
            }
        }
    }

    data class State(
        val isAuthSkip: Boolean = false,
    )
}