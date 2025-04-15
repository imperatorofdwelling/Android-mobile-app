package com.imperatorofdwelling.android.presentation.ui.landlord.creating

import androidx.lifecycle.viewModelScope
import com.imperatorofdwelling.android.domain.stays.usecases.GetCreatingHelpUseCase
import com.imperatorofdwelling.android.domain.stays.usecases.UpdateCreatingHelpUseCase
import com.imperatorofdwelling.android.presentation.ui.common.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CreatingViewModel(
    private val getCreatingHelpUseCase: GetCreatingHelpUseCase,
    private val updateCreatingHelpUseCase: UpdateCreatingHelpUseCase
) : BaseViewModel<CreatingViewModel.State>(State()) {

    init{
        initState()
    }

    private fun initState(){
        viewModelScope.launch(Dispatchers.IO){
            getCreatingHelpUseCase().collect{ value ->
                _state.update{
                    it.copy(showCreatingHelp = value)
                }
            }
        }
    }

    fun updateCreatingHelpScreen(value: Boolean){
        updateCreatingHelpUseCase(value)
    }

    data class State(
        val showCreatingHelp: Boolean = false
    )
}