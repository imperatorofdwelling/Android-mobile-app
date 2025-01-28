package com.imperatorofdwelling.android.presentation.ui.apart_detail

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.imperatorofdwelling.android.domain.NetworkResult
import com.imperatorofdwelling.android.domain.favorites.usecases.AddToFavouritesUseCase
import com.imperatorofdwelling.android.domain.favorites.usecases.DeleteFavouritesUseCase
import com.imperatorofdwelling.android.domain.stays.usecases.GetStayByIdUseCase
import com.imperatorofdwelling.android.domain.user.usecases.IsRegisteredUseCase
import com.imperatorofdwelling.android.presentation.entities.Dwelling
import com.imperatorofdwelling.android.presentation.entities.Review
import com.imperatorofdwelling.android.presentation.entities.amenityListMock
import com.imperatorofdwelling.android.presentation.entities.dwelling.Amenity
import com.imperatorofdwelling.android.presentation.entities.dwelling.mapper.DwellingViewModelMapper
import com.imperatorofdwelling.android.presentation.entities.reviewListMock
import com.imperatorofdwelling.android.presentation.ui.common.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ApartDetailViewModel(
    val addToFavouritesUseCase: AddToFavouritesUseCase,
    val deleteFavouritesUseCase: DeleteFavouritesUseCase,
    val isRegisteredUseCase: IsRegisteredUseCase,
    val getStayByIdUseCase: GetStayByIdUseCase
) : BaseViewModel<ApartDetailViewModel.State>(State()) {

    fun setDwellingId(dwellingId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                when(val result = getStayByIdUseCase(dwellingId)) {
                    is NetworkResult.Success -> {
                        _state.update {
                            it.copy(
                                dwellingItem = DwellingViewModelMapper.transform(result.value)
                            )
                        }
                    }
                    is NetworkResult.Error -> {
                        Log.e("GetStayByIdError", result.errorMessage)
                    }
                }
            }.onFailure { e ->
                Log.e("ServerError", e.message.toString())
            }
        }
    }

    suspend fun onLikeClick(stayId: String, isAdd: Boolean?): Boolean {
        if(isAdd == null) return false
        return withContext(Dispatchers.IO) {
            runCatching {
                if (!isRegisteredUseCase()) {
                    _state.update {
                        it.copy(showLoginNotification = true)
                    }
                }
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
                                    dwellingItem = _state.value.dwellingItem?.copy(
                                        isLiked = isAdd
                                    ),
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

    fun onDismissLogin() {
        _state.update {
            it.copy(showLoginNotification = false)
        }
    }

    data class State(
        val amenityList: List<Amenity> = amenityListMock,
        val showLoginNotification: Boolean = false,
        val description: String = "",
        val mark: Double? = null,
        val dwellingItem: Dwelling? = null,
        val reviews: List<Review> = reviewListMock
    )
}