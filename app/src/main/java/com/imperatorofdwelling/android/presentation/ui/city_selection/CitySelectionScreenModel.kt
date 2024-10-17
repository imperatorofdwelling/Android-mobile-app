package com.imperatorofdwelling.android.presentation.ui.city_selection

import androidx.compose.runtime.Immutable
import androidx.core.text.isDigitsOnly
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.hilt.ScreenModelFactory
import com.imperatorofdwelling.android.domain.cities.usecases.GetDefaultCityUseCase
import com.imperatorofdwelling.android.domain.cities.usecases.SearchCityUseCase
import com.imperatorofdwelling.android.domain.cities.usecases.SetDefaultCityUseCase
import com.imperatorofdwelling.android.presentation.annotations.ImpOfDwellingViewModel
import com.imperatorofdwelling.android.presentation.entities.cities.CityViewModelEntity
import com.imperatorofdwelling.android.presentation.entities.cities.mapper.CityDomainMapper
import com.imperatorofdwelling.android.presentation.entities.cities.mapper.CityViewModelMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@ImpOfDwellingViewModel
class CitySelectionScreenModel @Inject constructor(
    private val searchCityUseCase: SearchCityUseCase,
    private val setDefaultCityUseCase: SetDefaultCityUseCase,
    private val getDefaultCityUseCase: GetDefaultCityUseCase
) : ScreenModel {

    companion object {
        private const val EMPTY_CITY_NAME = ""
    }

    private val _state = MutableStateFlow(CitySelectionState())
    val state: StateFlow<CitySelectionState> get() = _state

    init {
        initState()
    }

    override fun onDispose() {
        super.onDispose()
    }


    private var lastSearchName = EMPTY_CITY_NAME
    fun searchCity(name: String?) {
        if (name?.isDigitsOnly() == true) {
            return
        }
        val searchRes = searchCityUseCase.invoke(name ?: EMPTY_CITY_NAME)
        _state.value =
            _state.value.copy(
                searchResults = CityViewModelMapper.transform(searchRes)
            )
        lastSearchName = name ?: EMPTY_CITY_NAME
    }

    fun setDefaultCity(city: CityViewModelEntity?) {
        if (city != null) {
            setDefaultCityUseCase(CityDomainMapper.transform(city)!!)
        }
        if (getDefaultCityUseCase() != null) {
            _state.value =
                _state.value.copy(defaultCity = CityViewModelMapper.transform(getDefaultCityUseCase())!!)
        }
        _state.value = _state.value.copy(
            searchResults = CityViewModelMapper.transform(
                searchCityUseCase.invoke(lastSearchName)
            )
        )
    }

    private fun initState() {
        val defaultCity = CityViewModelMapper.transform(getDefaultCityUseCase())
        defaultCity?.let {
            _state.value = _state.value.copy(defaultCity = defaultCity)
        }
    }

    @Immutable
    data class CitySelectionState(
        val searchResults: List<CityViewModelEntity> = emptyList(),
        val defaultCity: CityViewModelEntity? = null
    )

    class Factory @Inject constructor(
        private val searchCityUseCase: SearchCityUseCase,
        private val setDefaultCityUseCase: SetDefaultCityUseCase,
        private val getDefaultCityUseCase: GetDefaultCityUseCase
    ) : ScreenModelFactory {

        @Suppress("UNCHECKED_CAST")
        fun <T : ScreenModel> create(modelClass: Class<T>): T {

            if(modelClass.isAnnotationPresent(ImpOfDwellingViewModel::class.java)){
                return CitySelectionScreenModel(
                    searchCityUseCase,
                    setDefaultCityUseCase,
                    getDefaultCityUseCase
                ) as T
            }
            throw IllegalArgumentException("Unknown ScreenModel class: ${modelClass.name}")
        }
    }
}