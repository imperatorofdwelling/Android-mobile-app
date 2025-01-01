package com.imperatorofdwelling.android.presentation.ui.common

import androidx.lifecycle.ViewModel
import com.imperatorofdwelling.android.presentation.ui.utils.LCE
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<S>(initialState: S) : ViewModel() {
    protected val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    protected val _lce = MutableStateFlow<LCE>(LCE.Idle)
    val lce = _lce.asStateFlow()
}