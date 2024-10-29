    package com.imperatorofdwelling.android.presentation.ui.home_screen

    import cafe.adriel.voyager.core.model.ScreenModel
    import cafe.adriel.voyager.hilt.ScreenModelFactory
    import com.imperatorofdwelling.android.domain.cities.usecases.GetDefaultCityUseCase
    import com.imperatorofdwelling.android.presentation.annotations.ImpOfDwellingViewModel
    import com.imperatorofdwelling.android.presentation.entities.cities.CityViewModelEntity
    import com.imperatorofdwelling.android.presentation.entities.cities.mapper.CityViewModelMapper
    import com.imperatorofdwelling.android.presentation.entities.dwelling.Adult
    import com.imperatorofdwelling.android.presentation.entities.dwelling.Babies
    import com.imperatorofdwelling.android.presentation.entities.dwelling.Children
    import com.imperatorofdwelling.android.presentation.entities.dwelling.Pets
    import com.imperatorofdwelling.android.presentation.entities.dwelling.Rooms
    import kotlinx.coroutines.flow.MutableStateFlow
    import kotlinx.coroutines.flow.StateFlow
    import javax.annotation.concurrent.Immutable
    import javax.inject.Inject

    @ImpOfDwellingViewModel
    class HomeScreenModel @Inject constructor(
        private val getDefaultCityUseCase: GetDefaultCityUseCase
    ) : ScreenModel {

        private val _state = MutableStateFlow(HomeScreenState())
        val state: StateFlow<HomeScreenState> get() = _state

        init {
            initState()
        }

        private fun initState() {
            updateDefaultCity()
            updateCounts()
        }

        fun updateCounts(){
            _state.value = _state.value.copy(adultCount = Adult.count)
            _state.value = _state.value.copy(roomsCount = Rooms.count)
            _state.value = _state.value.copy(childrenCount = Children.count)
            _state.value = _state.value.copy(babiesCount = Babies.count)
            _state.value = _state.value.copy(petsCount = Pets.count)
        }

        fun updateDefaultCity(){
            val defaultCity = CityViewModelMapper.transform(getDefaultCityUseCase())
            defaultCity?.let {
                _state.value = _state.value.copy(defaultCity = defaultCity)
            }
        }


        @Immutable
        data class HomeScreenState(
            val defaultCity: CityViewModelEntity? = null,
            val adultCount: Int = 0,
            val roomsCount: Int = 0,
            val childrenCount: Int = 0,
            val babiesCount: Int = 0,
            val petsCount: Int = 0
        )

        class Factory @Inject constructor(
            private val getDefaultCityUseCase: GetDefaultCityUseCase
        ) : ScreenModelFactory {
            fun <T : ScreenModel> create(screenModel: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                if (screenModel.isAnnotationPresent(ImpOfDwellingViewModel::class.java)) {
                    return HomeScreenModel(
                        getDefaultCityUseCase
                    ) as T
                }
                throw IllegalArgumentException()
            }
        }
    }