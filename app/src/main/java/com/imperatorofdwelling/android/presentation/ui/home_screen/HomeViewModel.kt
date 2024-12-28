package com.imperatorofdwelling.android.presentation.ui.home_screen

import android.util.Log
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.viewModelScope
import com.google.errorprone.annotations.Immutable
import com.imperatorofdwelling.android.domain.NetworkResult
import com.imperatorofdwelling.android.domain.locations.entities.City
import com.imperatorofdwelling.android.domain.locations.usecases.GetDefaultCityUseCase
import com.imperatorofdwelling.android.domain.locations.usecases.SearchCityUseCase
import com.imperatorofdwelling.android.domain.locations.usecases.SetDefaultCityUseCase
import com.imperatorofdwelling.android.domain.stays.entities.Stay
import com.imperatorofdwelling.android.domain.stays.usecases.GetAllStaysUseCase
import com.imperatorofdwelling.android.domain.stays.usecases.GetMainImageUseCase
import com.imperatorofdwelling.android.domain.stays.usecases.GetStaysByLocationUseCase
import com.imperatorofdwelling.android.presentation.entities.Dwelling
import com.imperatorofdwelling.android.presentation.entities.dwelling.Adults
import com.imperatorofdwelling.android.presentation.entities.dwelling.Babies
import com.imperatorofdwelling.android.presentation.entities.dwelling.Children
import com.imperatorofdwelling.android.presentation.entities.dwelling.Pets
import com.imperatorofdwelling.android.presentation.entities.dwelling.Properties
import com.imperatorofdwelling.android.presentation.entities.dwelling.Rooms
import com.imperatorofdwelling.android.presentation.entities.dwelling.TypeOfDwelling
import com.imperatorofdwelling.android.presentation.entities.dwelling.mapper.DwellingViewModelMapper
import com.imperatorofdwelling.android.presentation.ui.common.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getDefaultCityUseCase: GetDefaultCityUseCase,
    private val searchCityUseCase: SearchCityUseCase,
    private val setDefaultCityUseCase: SetDefaultCityUseCase,
    private val getAllStaysUseCase: GetAllStaysUseCase,
    private val getMainImageUseCase: GetMainImageUseCase,
    private val getStaysByLocationUseCase: GetStaysByLocationUseCase
) : BaseViewModel<HomeViewModel.State>(State()) {

    init {
        initState()
    }

    private fun initState() {
        initDefaultCity()
        updateCounts()
        initStays()
    }

    private fun initStays() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                lateinit var result: NetworkResult<List<Stay>>
                if(_state.value.defaultCity != null){
                    result = getStaysByLocationUseCase(locationId = _state.value.defaultCity!!.id)
                } else {
                    result = getAllStaysUseCase()
                }
                when (result) {
                    is NetworkResult.Success -> {
                        _state.update {
                            it.copy(dwellingList = DwellingViewModelMapper.transform(result.value))
                        }
                    }
                    is NetworkResult.Error -> {
                        Log.e("GetStaysError", result.errorMessage)
                    }
                }
            }.onFailure { e ->
                Log.e("ServerError", e.message.toString())
            }
        }.invokeOnCompletion {
            initImages()
        }
    }

    private fun initImages() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                _state.value.dwellingList.mapIndexed { index, item ->
                    when (val result = getMainImageUseCase(item.id)) {
                        is NetworkResult.Success -> {
                            _state.value.dwellingList[index].imageUrl = result.value
                        }

                        is NetworkResult.Error -> {
                            Log.e("GetStaysError", result.errorMessage)
                        }
                    }
                }
            }.onFailure { e ->
                Log.e("ServerError", e.message.toString())
            }
        }.invokeOnCompletion {
            _state.value.dwellingList.map { item ->
                Log.e("Stays: ", item.toString())
            }
        }
    }

    fun onSearchValueChange(name: String) {
        _state.update { it.copy(searchQuery = name) }
        searchCity(name)
    }

    private fun searchCity(name: String) {
        if (name.isDigitsOnly()) {
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                when (val searchRes = searchCityUseCase.invoke(name)) {
                    is NetworkResult.Success -> {
                        _state.update {
                            it.copy(
                                searchResults = searchRes.value
                            )
                        }
                    }

                    is NetworkResult.Error -> {
                        Log.e("SearchCityError", searchRes.errorMessage)
                    }
                }

            }.onFailure { e ->
                Log.e("SearchCityError", e.message.toString())
            }
        }
    }

    fun setDefaultCity(newDefaultCity: City) {
        setDefaultCityUseCase(newDefaultCity)
        _state.value =
            _state.value.copy(
                defaultCity = getDefaultCityUseCase()
            )
        updateShowCitySelection(false)
        _state.update {
            it.copy(searchQuery = newDefaultCity.city)
        }
        initStays()
    }


    fun updateCounts() {
        _state.value = _state.value.copy(adultCount = Adults.count)
        _state.value = _state.value.copy(roomsCount = Rooms.count)
        _state.value = _state.value.copy(childrenCount = Children.count)
        _state.value = _state.value.copy(babiesCount = Babies.count)
        _state.value = _state.value.copy(petsCount = Pets.count)

        updatePropertyList(Adults)
        updatePropertyList(Rooms)
        updatePropertyList(Children)
        updatePropertyList(Babies)
        updatePropertyList(Pets)
    }


    private fun updatePropertyList(property: Properties) {
        if (property.count != 0 && property !in _state.value.selectedProperties) {
            val newProperties = _state.value.selectedProperties.toMutableList()
            newProperties.add(property)
            _state.value = _state.value.copy(selectedProperties = newProperties)
        }
        if (property.count == 0 && property in _state.value.selectedProperties) {
            val newProperties = _state.value.selectedProperties.toMutableList()
            newProperties.remove(property)
            _state.value = _state.value.copy(selectedProperties = newProperties)
        }
    }

    private fun initDefaultCity() {
        val defaultCity = getDefaultCityUseCase()
        _state.update {
            it.copy(
                defaultCity = defaultCity
            )
        }
    }

    fun updateSelectedTypes(typeOfDwelling: TypeOfDwelling) {
        val newSelectedTypes = _state.value.selectedTypes.toMutableList()
        if (typeOfDwelling in _state.value.selectedTypes) {
            newSelectedTypes.remove(typeOfDwelling)
        } else {
            newSelectedTypes.add(typeOfDwelling)
        }
        _state.value = _state.value.copy(selectedTypes = newSelectedTypes)
    }

    fun onDismissTypes() {
        _state.value = _state.value.copy(selectedTypes = emptyList())
    }

    fun onDismissResidents() {
        val newProperties = _state.value.selectedProperties.toMutableList()
        newProperties.map { item ->
            item.setEmptyCount()
        }
        updateCounts()
        newProperties.clear()
        _state.value = _state.value.copy(selectedProperties = newProperties)
    }

    fun areTypesSelect(): Boolean {
        return _state.value.selectedTypes.isNotEmpty()
    }

    fun selectedTypesString(): String {
        val res: StringBuilder = StringBuilder()
        _state.value.selectedTypes.mapIndexed { index, item ->
            if (index != _state.value.selectedTypes.size - 1) {
                res.append(item.toString())
                res.append(", ")
            } else {
                res.append(item.toString())
            }
        }
        return res.toString()
    }


    fun areResidentsSelect(): Boolean {
        return _state.value.selectedProperties.isNotEmpty()
    }

    fun selectedResidentsString(): String {
        val res = StringBuilder()

        _state.value.selectedProperties.mapIndexed { index, item ->
            if (index != _state.value.selectedProperties.size - 1) {
                res.append(item.toString())
                res.append(", ")
            } else {
                res.append(item.toString())
            }
        }

        return res.toString()
    }

    fun updateShowCitySelection(show: Boolean) {
        _state.update {
            it.copy(showCitySelection = show)
        }
    }

    @Immutable
    data class State(
        val defaultCity: City? = null,
        val adultCount: Int = 0,
        val roomsCount: Int = 0,
        val childrenCount: Int = 0,
        val babiesCount: Int = 0,
        val petsCount: Int = 0,
        val selectedProperties: List<Properties> = emptyList(),
        val selectedTypes: List<TypeOfDwelling> = emptyList(),
        val searchResults: List<City?> = emptyList(),
        val searchQuery: String = "",
        val showCitySelection: Boolean = false,
        val dwellingList: List<Dwelling> = emptyList(),
    )
}