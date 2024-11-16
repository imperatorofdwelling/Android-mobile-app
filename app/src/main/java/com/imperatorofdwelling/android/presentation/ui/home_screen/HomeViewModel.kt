package com.imperatorofdwelling.android.presentation.ui.home_screen

import com.google.errorprone.annotations.Immutable
import com.imperatorofdwelling.android.domain.cities.usecases.GetDefaultCityUseCase
import com.imperatorofdwelling.android.presentation.entities.cities.CityViewModelEntity
import com.imperatorofdwelling.android.presentation.entities.cities.mapper.CityViewModelMapper
import com.imperatorofdwelling.android.presentation.entities.dwelling.Adults
import com.imperatorofdwelling.android.presentation.entities.dwelling.Babies
import com.imperatorofdwelling.android.presentation.entities.dwelling.Children
import com.imperatorofdwelling.android.presentation.entities.dwelling.Pets
import com.imperatorofdwelling.android.presentation.entities.dwelling.Properties
import com.imperatorofdwelling.android.presentation.entities.dwelling.Rooms
import com.imperatorofdwelling.android.presentation.entities.dwelling.TypeOfDwelling
import com.imperatorofdwelling.android.presentation.ui.common.BaseViewModel

class HomeViewModel(
    private val getDefaultCityUseCase: GetDefaultCityUseCase
) : BaseViewModel<HomeViewModel.State>(State()) {

    init {
        initState()
    }

    private fun initState() {
        updateDefaultCity()
        updateCounts()
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

    fun updateDefaultCity() {
        val defaultCity = CityViewModelMapper.transform(getDefaultCityUseCase())
        defaultCity?.let {
            _state.value = _state.value.copy(defaultCity = defaultCity)
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
    fun onDismissResidents(){
        val newProperties = _state.value.selectedProperties.toMutableList()
        newProperties.map{ item ->
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

    @Immutable
    data class State(
        val defaultCity: CityViewModelEntity? = null,
        val adultCount: Int = 0,
        val roomsCount: Int = 0,
        val childrenCount: Int = 0,
        val babiesCount: Int = 0,
        val petsCount: Int = 0,
        val selectedProperties: List<Properties> = emptyList(),
        val selectedTypes: List<TypeOfDwelling> = emptyList()
    )
}