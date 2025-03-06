package com.imperatorofdwelling.android.presentation.ui.landlord.main_screen

import com.imperatorofdwelling.android.presentation.ui.common.BaseViewModel

class MainViewModel: BaseViewModel<MainViewModel.State>(State()) {

    data class State(
        val d: Int = 0
    )
}