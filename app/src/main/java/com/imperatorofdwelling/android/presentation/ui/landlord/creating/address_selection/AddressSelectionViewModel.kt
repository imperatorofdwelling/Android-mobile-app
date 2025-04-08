package com.imperatorofdwelling.android.presentation.ui.landlord.creating

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.imperatorofdwelling.android.domain.NetworkResult
import com.imperatorofdwelling.android.domain.locations.entities.SearchResult
import com.imperatorofdwelling.android.domain.locations.usecases.SearchAddressUseCase
import com.imperatorofdwelling.android.presentation.ui.common.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

class AddressSelectionViewModel (
    private val searchAddressUseCase: SearchAddressUseCase
) : BaseViewModel<AddressSelectionViewModel.State>(State()) {

    private var isSearchingCoolDown = false
    private var updatingSearch = false
    private val searchMutex = Mutex()
    fun onSearchAddress(q: String) {
        _state.update { it.copy(searchString = q) }

        if (isSearchingCoolDown) {
            updatingSearch = true
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            searchMutex.withLock {
                runCatching {
                    isSearchingCoolDown = true
                    when (val result = searchAddressUseCase(_state.value.searchString)) {
                        is NetworkResult.Success -> {
                            withContext(Dispatchers.Main) {
                                _state.update {
                                    it.copy(searchResult = result.value)
                                }
                            }
                        }

                        is NetworkResult.Error -> {
                            Log.e("SearchResultError", result.errorMessage)
                        }
                    }
                    delay(1000)
                    isSearchingCoolDown = false
                    if(updatingSearch){
                        updatingSearch = false
                        onSearchAddress(_state.value.searchString)
                    }
                }.onFailure { e ->
                    Log.e("ServerError", e.message.toString())
                }
            }
        }
    }

    fun setAddress(address: SearchResult) {
        _state.update {
            it.copy(address = address)
        }
    }

    fun updateShowAddressSelection(showAddressSelection: Boolean) {
        _state.update {
            it.copy(showAddressSelection = showAddressSelection)
        }
    }

    fun clearSearchString(){
        _state.update { it.copy(searchString = "") }
    }

    data class State(
        val searchString: String = "",
        val searchResult: List<SearchResult?>? = null,
        val showAddressSelection: Boolean = false,
        val address: SearchResult? = null,
    )
}