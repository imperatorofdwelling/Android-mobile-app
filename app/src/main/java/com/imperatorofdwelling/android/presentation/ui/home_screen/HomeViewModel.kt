package com.imperatorofdwelling.android.presentation.ui.home_screen

import com.google.errorprone.annotations.Immutable
import com.imperatorofdwelling.android.domain.cities.usecases.GetDefaultCityUseCase
import com.imperatorofdwelling.android.presentation.entities.cities.CityViewModelEntity
import com.imperatorofdwelling.android.presentation.entities.cities.mapper.CityViewModelMapper
import com.imperatorofdwelling.android.presentation.entities.dwelling.Adult
import com.imperatorofdwelling.android.presentation.entities.dwelling.Babies
import com.imperatorofdwelling.android.presentation.entities.dwelling.Children
import com.imperatorofdwelling.android.presentation.entities.dwelling.Pets
import com.imperatorofdwelling.android.presentation.entities.dwelling.Rooms
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
        _state.value = _state.value.copy(adultCount = Adult.count)
        _state.value = _state.value.copy(roomsCount = Rooms.count)
        _state.value = _state.value.copy(childrenCount = Children.count)
        _state.value = _state.value.copy(babiesCount = Babies.count)
        _state.value = _state.value.copy(petsCount = Pets.count)
    }

    fun updateDefaultCity() {
        val defaultCity = CityViewModelMapper.transform(getDefaultCityUseCase())
        defaultCity?.let {
            _state.value = _state.value.copy(defaultCity = defaultCity)
        }
    }


    @Immutable
    data class State(
        val defaultCity: CityViewModelEntity? = null,
        val adultCount: Int = 0,
        val roomsCount: Int = 0,
        val childrenCount: Int = 0,
        val babiesCount: Int = 0,
        val petsCount: Int = 0
    )
}