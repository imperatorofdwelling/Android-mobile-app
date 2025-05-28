package com.imperatorofdwelling.android.presentation.ui.landlord.creating

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.viewModelScope
import com.imperatorofdwelling.android.domain.NetworkResult
import com.imperatorofdwelling.android.domain.locations.entities.SearchResult
import com.imperatorofdwelling.android.domain.locations.usecases.GetSavedAddressUseCase
import com.imperatorofdwelling.android.domain.stays.entities.Stay
import com.imperatorofdwelling.android.domain.stays.usecases.CreateStayUseCase
import com.imperatorofdwelling.android.domain.stays.usecases.GetAllAmenitiesUseCase
import com.imperatorofdwelling.android.domain.stays.usecases.GetCreatingHelpUseCase
import com.imperatorofdwelling.android.domain.stays.usecases.UpdateCreatingHelpUseCase
import com.imperatorofdwelling.android.presentation.entities.Period
import com.imperatorofdwelling.android.presentation.entities.dwelling.Apartment
import com.imperatorofdwelling.android.presentation.entities.dwelling.Hotel
import com.imperatorofdwelling.android.presentation.entities.dwelling.House
import com.imperatorofdwelling.android.presentation.ui.common.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CreatingViewModel(
    private val getCreatingHelpUseCase: GetCreatingHelpUseCase,
    private val updateCreatingHelpUseCase: UpdateCreatingHelpUseCase,
    private val getSavedAddressUseCase: GetSavedAddressUseCase,
    private val getAllAmenitiesUseCase: GetAllAmenitiesUseCase,
    private val createStayUseCase: CreateStayUseCase
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
        initAmenities()
    }

    fun initAmenities() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getAllAmenitiesUseCase()
            when(result){
                is NetworkResult.Success -> {
                    _state.update { it.copy(amenities = result.value) }
                }
                is NetworkResult.Error -> {
                    Log.d("CreatingViewModel", "error = ${result.errorMessage}")
                }
            }
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

    fun onOwnersRulesChanged(value: String) {
        _state.update { it.copy(ownersRules = value) }
    }

    fun onCancellationPolicyChanged(value: String) {
        _state.update { it.copy(cancellationPolicy = value) }
    }

    fun onDescribePropertyChanged(value: String) {
        _state.update { it.copy(describeProperty = value) }
    }

    fun onPriceChanged(value: String) {
        _state.update { it.copy(price = value) }
    }

    fun onPeriodChanged(value: Period) {
        _state.update { it.copy(period = value) }
    }

    fun isFirstStepComplete(): Boolean {
        val title = _state.value.title.trim().isNotEmpty()
        val house = _state.value.houseSelected
        val apartment = _state.value.apartmentSelected
        val hotel = _state.value.hotelSelected
        val address = _state.value.currentAddress != null
        val countOfRooms = _state.value.numberOfRoomsSelected.isNotEmpty()
        val countOfBeds = _state.value.numberOfBedsSelected.isNotEmpty()
        return title && (house || apartment || hotel) && address && countOfRooms && countOfBeds
    }

    fun isSecondStepComplete(): Boolean {
        return _imageUris.size >= 3
    }

    fun isThirdStepComplete(): Boolean {
        return true
    }

    fun isFourthStepComplete(): Boolean {
        return _state.value.price.isNotEmpty() && _state.value.period != null
    }

    fun onAmenityClicked(amenity: String) {
        val list = state.value.amenitySelected.toMutableList()
        if (list.contains(amenity)) {
            list.remove(amenity)
        } else {
            list.add(amenity)
        }
        _state.update { it.copy(amenitySelected = list) }
    }

    fun onCreateStay(){
        val typeString =
            if(_state.value.houseSelected) House.toString()
            else if(_state.value.apartmentSelected) Apartment.toString()
            else Hotel.toString()

        val stay = Stay(
            id = "",
            name = _state.value.title,
            userId = "",
            type = typeString,
            street = _state.value.currentAddress?.toRussianString() ?: "",
            room = _state.value.numberOfRoomsSelected,
            isSmokingProhibited = false,
            entrance = _state.value.approachNumberValue,
            locationId = "",
            numberOfBeds = _state.value.numberOfBedsSelected.toIntOrNull() ?: 0,
            numberOfBedrooms = _state.value.numberOfRoomsSelected.toIntOrNull() ?: 0,
            guests = _state.value.residentsCount,
            square = 0,
            rating = 0.0,
            house = _state.value.apartmentNumberValue,
            floor = "1",
            price = _state.value.price.toIntOrNull() ?: 0,
            createdAt = null,
            updatedAt = null,
            isFavourites = false,
            numberOfBathrooms = 1, // TODO
        )
        viewModelScope.launch(Dispatchers.IO) {
            when(val response = createStayUseCase(stay)) {
                is NetworkResult.Success -> {
                    Log.d("StayCreating", response.value)
                }
                is NetworkResult.Error -> {
                    Log.d("StayCreating", response.errorMessage)
                }
            }
        }
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
        val ownersRules: String = "",
        val cancellationPolicy: String = "",
        val describeProperty: String = "",
        val price: String = "",
        val period: Period? = null,
        val amenities: List<String> = emptyList(),
        val amenitySelected: List<String> = emptyList(),
    )
}