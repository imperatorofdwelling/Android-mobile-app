package com.imperatorofdwelling.android.presentation.ui.stay_list_screen

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.imperatorofdwelling.android.domain.NetworkResult
import com.imperatorofdwelling.android.domain.favorites.usecases.AddToFavouritesUseCase
import com.imperatorofdwelling.android.domain.favorites.usecases.DeleteFavouritesUseCase
import com.imperatorofdwelling.android.domain.stays.usecases.GetMainImageUseCase
import com.imperatorofdwelling.android.presentation.entities.Dwelling
import com.imperatorofdwelling.android.presentation.ui.common.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StayListViewModel(
    private val addToFavouritesUseCase: AddToFavouritesUseCase,
    private val deleteFavouritesUseCase: DeleteFavouritesUseCase,
    private val getMainImageUseCase: GetMainImageUseCase
) : BaseViewModel<StayListViewModel.State>(State()) {

    fun updateList(list: List<Dwelling>){
        _state.update{
            it.copy(listStay = list)
        }
        initImages()
    }


    suspend fun onLikeClick(stayId: String, isAdd: Boolean): Boolean {
        return withContext(Dispatchers.IO) {
            runCatching {
                val result = if (isAdd) {
                    addToFavouritesUseCase(stayId)
                } else {
                    deleteFavouritesUseCase(stayId)
                }
                when (result) {
                    is NetworkResult.Success -> {
                        withContext(Dispatchers.Main) {
                            _state.update {
                                it.copy(
                                    listStay = updateStayList(
                                        stayId, isAdd
                                    )
                                )
                            }
                        }
                        true
                    }

                    is NetworkResult.Error -> {
                        Log.e("AddFavouritesError", result.errorMessage)
                        false
                    }
                }

            }.getOrElse { e ->
                Log.e("ServerError", e.message.toString())
                false
            }
        }
    }

    private fun updateStayList(stayId: String, isLiked: Boolean): List<Dwelling> {
        val newList = _state.value.listStay.map { dwelling: Dwelling ->
            if (dwelling.id == stayId) {
                dwelling.copy(isLiked = isLiked)
            } else {
                dwelling
            }
        }
        return newList
    }

    private fun initImages() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                _state.value.listStay.mapIndexed { index, item ->
                    when (val result = getMainImageUseCase(item.id)) {
                        is NetworkResult.Success -> {
                            _state.value.listStay[index].imageUrl = result.value
                        }

                        is NetworkResult.Error -> {
                            Log.e("GetStaysError", result.errorMessage)
                        }
                    }
                }
            }.onFailure { e ->
                Log.e("ServerError", e.message.toString())
            }
        }.invokeOnCompletion { _state.update {it.copy(isImagesLoaded = true)} }
    }

    data class State(
        val listStay: List<Dwelling> = emptyList(),
        val isImagesLoaded: Boolean = false
    )
}