package com.imperatorofdwelling.android.presentation.ui.city_selection

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.imperatorofdwelling.android.domain.entities.City
import com.imperatorofdwelling.android.domain.usecases.GetDefaultCityUseCase
import com.imperatorofdwelling.android.domain.usecases.SearchCityUseCase
import com.imperatorofdwelling.android.domain.usecases.SetDefaultCityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CitySelectionViewModel @Inject constructor(
    private val searchCityUseCase: SearchCityUseCase,
    private val setDefaultCityUseCase: SetDefaultCityUseCase,
    private val getDefaultCityUseCase: GetDefaultCityUseCase
) : ViewModel() {
    private val _searchCityResult = MutableLiveData<List<City>>()
    val searchCityResult: LiveData<List<City>> get() = _searchCityResult

    private val _defaultCity = MutableLiveData<City>().apply {
        value = getDefaultCityUseCase()
    }
    val defaultCity: LiveData<City> get() = _defaultCity


    private var lastSearchName = ""
    fun searchCity(name: String?) {
        if (name?.isDigitsOnly() == true) {
            return
        }
        _searchCityResult.postValue(
            searchCityUseCase.invoke(name ?: "")
        )
        lastSearchName = name ?: ""
    }

    fun setDefaultCity(city: City?) {
        if (city != null) {
            setDefaultCityUseCase(city)
        }
        if (getDefaultCityUseCase() != null) {
            _defaultCity.postValue(
                getDefaultCityUseCase()!!
            )
        }
        _searchCityResult.postValue(
            searchCityUseCase.invoke(lastSearchName)
        )
    }
}