package com.imperatorofdwelling.android.presentation.ui.favorites

import androidx.compose.runtime.Immutable
import com.imperatorofdwelling.android.domain.cities.entities.City
import com.imperatorofdwelling.android.domain.favorites.entities.FavoriteGroup
import com.imperatorofdwelling.android.presentation.ui.common.BaseViewModel

class FavoritesViewModel : BaseViewModel<FavoritesViewModel.State>(State()) {

    init {
        initState()
    }

    private fun initState() {
        /*
        *  MockData
        *
        * */
//        val mockFavoriteGroup = FavoriteGroup(
//            City(1, "Москва", 100f),
//            listOf(
//                dwellingMock, dwellingMock, dwellingMock,
//                dwellingMock, dwellingMock
//            )
//        )

//        val favoritesList =
//            listOf<FavoriteGroup>(
//                mockFavoriteGroup,
//                mockFavoriteGroup,
//                mockFavoriteGroup,
//                mockFavoriteGroup,
//                mockFavoriteGroup,
//                mockFavoriteGroup,
//                mockFavoriteGroup,
//                mockFavoriteGroup,
//                mockFavoriteGroup,
//                mockFavoriteGroup,
//                mockFavoriteGroup
//            )

        _state.value = _state.value.copy(favoriteGroups = emptyList())
    }

    @Immutable
    data class State(
        val favoriteGroups: List<FavoriteGroup>? = null
    )
}