package com.imperatorofdwelling.android.presentation.ui.favorites

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.lifecycle.viewModelScope
import com.imperatorofdwelling.android.domain.NetworkResult
import com.imperatorofdwelling.android.domain.favorites.usecases.GetAllFavouritesUseCase
import com.imperatorofdwelling.android.presentation.entities.Dwelling
import com.imperatorofdwelling.android.presentation.entities.dwelling.mapper.DwellingViewModelMapper
import com.imperatorofdwelling.android.presentation.ui.common.BaseViewModel
import com.imperatorofdwelling.android.presentation.ui.utils.LCE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritesViewModel(
    private val getAllFavouritesUseCase: GetAllFavouritesUseCase
) : BaseViewModel<FavoritesViewModel.State>(State()) {

    init {
        initState()
    }

    fun refreshScreen(){
        initState()
    }

    private fun initState() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                _lce.value = LCE.Loading
                when(val result = getAllFavouritesUseCase()){
                    is NetworkResult.Success -> {
                        withContext(Dispatchers.Main){
                            _state.update {it.copy(favoriteGroups = DwellingViewModelMapper.transformMap(result.value))}
                        }
                    }
                    is NetworkResult.Error -> {
                        Log.e("GetFavouritesError", result.errorMessage)
                    }
                }
                _lce.value = LCE.Idle
            }.onFailure { e ->
                Log.e("ServerError", e.message.toString())
            }
        }
    }

    @Immutable
    data class State(
        val favoriteGroups: Map<String, List<Dwelling>>? = null
    )
}