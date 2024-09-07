package com.imperatorofdwelling.android.presentation.ui.city_selection

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.imperatorofdwelling.android.domain.models.City
import com.imperatorofdwelling.android.domain.use_cases.SearchCityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CitySelectionViewModel @Inject constructor(
    private val searchCityUseCase: SearchCityUseCase
) : ViewModel() {
    private val _searchCityResult = MutableLiveData<List<City>>()
    val searchCityResult: LiveData<List<City>> get() = _searchCityResult

    fun searchCity(name: String?) {
        if (name?.isDigitsOnly() == true){
           return
        }
        _searchCityResult.postValue(
            searchCityUseCase.execute(name ?: "")
        )
    }
}