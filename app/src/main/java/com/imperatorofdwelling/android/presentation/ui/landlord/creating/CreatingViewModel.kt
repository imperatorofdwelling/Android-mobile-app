package com.imperatorofdwelling.android.presentation.ui.landlord.creating

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.viewModelScope
import com.imperatorofdwelling.android.domain.locations.entities.SearchResult
import com.imperatorofdwelling.android.domain.locations.usecases.GetSavedAddressUseCase
import com.imperatorofdwelling.android.domain.stays.usecases.GetCreatingHelpUseCase
import com.imperatorofdwelling.android.domain.stays.usecases.UpdateCreatingHelpUseCase
import com.imperatorofdwelling.android.presentation.ui.common.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CreatingViewModel(
    private val getCreatingHelpUseCase: GetCreatingHelpUseCase,
    private val updateCreatingHelpUseCase: UpdateCreatingHelpUseCase,
    private val getSavedAddressUseCase: GetSavedAddressUseCase,
) : BaseViewModel<CreatingViewModel.State>(State()) {

    init {
        initState()
    }

    private fun initState() {
        viewModelScope.launch(Dispatchers.IO) {
            launch {
                getCreatingHelpUseCase().collect { value ->
                    _state.update {
                        it.copy(showCreatingHelp = value)
                    }
                }
            }
            launch {
                getSavedAddressUseCase().collect { newValue ->
                    Log.d("CreatingViewModel", "address = $newValue")
                    _state.update {
                        it.copy(currentAddress = newValue)
                    }
                }
            }
        }
        _state.update {
            it.copy(
                numberOfRoomsList = listOf("1", "2", "3", "4", "5", "6+"),
                numberOfBedsList = listOf("1", "2", "3", "4", "5", "6+")
            )
        }
    }

    fun updateCreatingHelpScreen(value: Boolean) {
        updateCreatingHelpUseCase(value)
    }

    fun nextStep() {
        _state.update { it.copy(step = it.step + 1) }
    }

    fun onTitleChanged(value: String) {
        _state.update { it.copy(title = value) }
    }

    fun houseClick(value: Boolean) {
        _state.update {
            it.copy(
                houseSelected = value,
                apartmentSelected = false,
                hotelSelected = false
            )
        }
    }

    fun apartmentClick(value: Boolean) {
        _state.update {
            it.copy(
                apartmentSelected = value,
                hotelSelected = false,
                houseSelected = false
            )
        }
    }

    fun hotelClick(value: Boolean) {
        _state.update {
            it.copy(
                hotelSelected = value,
                apartmentSelected = false,
                houseSelected = false
            )
        }
    }

    fun onApproachNumberChanged(value: String) {
        _state.update { it.copy(approachNumberValue = value) }
    }

    fun onApartmentNumberChanged(value: String) {
        _state.update { it.copy(apartmentNumberValue = value) }
    }

    fun addressCancel() {
        _state.update { it.copy(currentAddress = null) }
    }

    fun onNextStep() {
        _state.update { it.copy(step = it.step + 1) }
    }

    fun onImageSelected(uri: Uri) {
        if (_imageUris.contains(uri)) return
        _imageUris.add(uri)
    }

    fun onImageCancel(uri: Uri) {
        _imageUris.remove(uri)
    }

    fun onReorder(imageList: List<Uri>) {
        _imageUris.clear()
        _imageUris.addAll(imageList)
    }

    fun previousStep() {
        if (_state.value.step > 1) {
            _state.update { it.copy(step = it.step - 1) }
        }
    }

    fun onNumberOfRoomsSelected(value: String) {
        _state.update { it.copy(numberOfRoomsSelected = value) }
    }

    fun onResidentsCountChanged(value: Int) {
        if (value < 1 || value > 20) return
        _state.update { it.copy(residentsCount = value) }
    }

    fun onNumberOfBedsSelected(value: String) {
        _state.update { it.copy(numberOfBedsSelected = value) }
    }
    private val _imageUris = mutableStateListOf<Uri>()
    val imageUris: List<Uri>
        get() = _imageUris

    data class State(
        val showCreatingHelp: Boolean = false,
        val step: Int = 1,
        val title: String = "",
        val houseSelected: Boolean = false,
        val apartmentSelected: Boolean = false,
        val hotelSelected: Boolean = false,
        val approachNumberValue: String = "",
        val apartmentNumberValue: String = "",
        val currentAddress: SearchResult? = null,
        val numberOfRoomsList: List<String> = emptyList(),
        val numberOfRoomsSelected: String = "",
        val numberOfBedsList: List<String> = emptyList(),
        val numberOfBedsSelected: String = "",
        val residentsCount: Int = 1,
    )
}