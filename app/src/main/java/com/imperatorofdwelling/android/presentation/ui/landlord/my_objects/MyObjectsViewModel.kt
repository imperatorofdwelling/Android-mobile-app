package com.imperatorofdwelling.android.presentation.ui.landlord.my_objects

import com.imperatorofdwelling.android.domain.stays.entities.Stay
import com.imperatorofdwelling.android.presentation.ui.common.BaseViewModel

class MyObjectsViewModel : BaseViewModel<MyObjectsViewModel.State>(State()) {

    data class State(
        val myObjectsList: List<Stay> = listOf()
    )
}