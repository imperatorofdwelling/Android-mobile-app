package com.imperatorofdwelling.android.presentation.ui.landlord.creating.address_selection

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.domain.locations.entities.SearchResult
import com.imperatorofdwelling.android.presentation.entities.dwelling.Apartment
import com.imperatorofdwelling.android.presentation.ui.apart_detail.components.MapPoint
import com.imperatorofdwelling.android.presentation.ui.components.OpenStreetMap
import com.imperatorofdwelling.android.presentation.ui.components.buttons.PrimaryButton
import com.imperatorofdwelling.android.presentation.ui.components.text_fields.EditTextTrailing
import com.imperatorofdwelling.android.presentation.ui.home_screen.components.AddressSelection
import org.koin.androidx.compose.koinViewModel
import org.osmdroid.util.GeoPoint

class AddressSelectionScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<AddressSelectionViewModel>()
        val screenState = viewModel.state.collectAsState()

        Scaffold(
            topBar = {
                TopBar(

                )
            },
        ) { paddingValues ->
            BodySelectionAddress(
                modifier = Modifier.padding(paddingValues),
                onCitySearch = viewModel::onSearchAddress,
                searchResult = screenState.value.searchResult ?: emptyList(),
                onSetAddress = viewModel::setAddress,
                searchString = screenState.value.searchString,
                onClickTrailing = viewModel::clearSearchString,
                currentAddress = screenState.value.address,
                saveMyAddress = viewModel::onSaveAddress
            )
        }
    }
}

@Composable
private fun TopBar(
    modifier: Modifier = Modifier,
) {

}

@Composable
private fun BodySelectionAddress(
    modifier: Modifier = Modifier,
    onCitySearch: (String) -> Unit,
    searchResult: List<SearchResult?>,
    searchString: String = "",
    saveMyAddress: () -> Unit,
    onClickTrailing: () -> Unit,
    onSetAddress: (SearchResult, String) -> Unit,
    currentAddress: SearchResult? = null
) {

    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        key(currentAddress) {
            OpenStreetMap(
                modifier = Modifier.fillMaxSize(),
                zoom = 15.0,
                touchable = true,
                geoPointCenter = GeoPoint(
                    currentAddress?.lat ?: 51.50853,
                    currentAddress?.lon ?: -0.12574
                ),
            )
        }
        Column(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 35.dp)
        ) {
            EditTextTrailing(
                modifier = Modifier.width(360.dp),
                trailingIcon = painterResource(id = R.drawable.cross),
                onValueChanged = {
                    onCitySearch(it)
                },
                value = searchString,
                onClickTrailing = onClickTrailing,
                icon = painterResource(id = R.drawable.map_item),
                maxLines = 4
            )

            AddressSelection(
                modifier = Modifier
                    .fillMaxWidth(),
                searchResults = searchResult,
                onAddressClick = onSetAddress,
                selectedAddress = currentAddress
            )
        }

        MapPoint(modifier = Modifier.align(Alignment.Center), dwellingType = Apartment)
        if(currentAddress != null) {
            val navigator = LocalNavigator.currentOrThrow
            PrimaryButton(Modifier.align(Alignment.BottomCenter), text = "Select", onClick = {
                saveMyAddress()
                navigator.pop()
            })
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}