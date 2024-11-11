package com.imperatorofdwelling.android.presentation.ui.city_selection

import androidx.compose.runtime.Immutable
import androidx.core.text.isDigitsOnly
import com.imperatorofdwelling.android.domain.cities.usecases.GetDefaultCityUseCase
import com.imperatorofdwelling.android.domain.cities.usecases.SearchCityUseCase
import com.imperatorofdwelling.android.domain.cities.usecases.SetDefaultCityUseCase
import com.imperatorofdwelling.android.presentation.entities.cities.CityViewModelEntity
import com.imperatorofdwelling.android.presentation.entities.cities.mapper.CityDomainMapper
import com.imperatorofdwelling.android.presentation.entities.cities.mapper.CityViewModelMapper
import com.imperatorofdwelling.android.presentation.ui.common.BaseViewModel
import kotlinx.coroutines.flow.update

private const val DEFAULT_CITY_NAME = ""

class CitySelectionViewModel(
    private val searchCityUseCase: SearchCityUseCase,
    private val setDefaultCityUseCase: SetDefaultCityUseCase,
    private val getDefaultCityUseCase: GetDefaultCityUseCase
) : BaseViewModel<CitySelectionViewModel.State>(State()) {

    init {
        initState()
    }

    fun onSearchValueChange(name: String) {
        _state.update { it.copy(searchQuery = name) }
        searchCity(name)
    }

    private fun searchCity(name: String) {
        if (name.isDigitsOnly()) {
            return
        }
        val searchRes = searchCityUseCase.invoke(name)
        _state.update {
            it.copy(
                searchResults = searchRes.map { city -> city.name }
            )
        }
    }

    fun setDefaultCity(cityName: String) {
        val newDefaultCity = CityViewModelEntity(
            name = cityName
        )
        setDefaultCityUseCase(CityDomainMapper.transform(newDefaultCity)!!)
        _state.value =
            _state.value.copy(
                defaultCityName = CityViewModelMapper.transform(
                    getDefaultCityUseCase()
                )!!.name
            )
    }

    private fun initState() {
        val defaultCity = CityViewModelMapper.transform(getDefaultCityUseCase())
        _state.update {
            it.copy(defaultCityName = defaultCity?.name ?: DEFAULT_CITY_NAME)
        }
    }

    @Immutable
    data class State(
        val searchResults: List<String> = emptyList(),
        val defaultCityName: String = DEFAULT_CITY_NAME,
        val searchQuery: String = ""
    )
}