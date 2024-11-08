package com.imperatorofdwelling.android.presentation.ui.city_selection

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.ui.city_selection.components.CityItem
import com.imperatorofdwelling.android.presentation.ui.components.IconTextField
import com.imperatorofdwelling.android.presentation.ui.components.SmallSpacer
import com.imperatorofdwelling.android.presentation.ui.theme.extraLargeDp
import com.imperatorofdwelling.android.presentation.ui.theme.mediumDp
import org.koin.androidx.compose.koinViewModel


class CitySelectionScreen(private val onCitySelectionCallBack: () -> Unit) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val viewModel = koinViewModel<CitySelectionViewModel>()
        val state = viewModel.state.collectAsState()
        CitySelectionBody(
            searchResults = state.value.searchResults,
            defaultCityName = state.value.defaultCityName,
            searchQuery = state.value.searchQuery,
            onCityClick = viewModel::setDefaultCity,
            onSearchValueChange = viewModel::onSearchValueChange,
            onBackClick = { navigator.pop() },
        )
    }

    @Composable
    fun CitySelectionBody(
        searchResults: List<String>,
        defaultCityName: String,
        searchQuery: String,
        onCityClick: (String) -> Unit,
        onSearchValueChange: (String) -> Unit,
        onBackClick: () -> Unit
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(horizontal = extraLargeDp)) {
            SmallSpacer()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                verticalAlignment = Alignment.Top,
            ) {
                Image(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .fillMaxHeight()
                        .clickable {
                            onBackClick()
                        },
                    imageVector = ImageVector.vectorResource(R.drawable.back_button),
                    contentDescription = stringResource(
                        R.string.button_back
                    ),
                    contentScale = ContentScale.FillBounds
                )

                IconTextField(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = mediumDp),
                    unfocusedIcon = painterResource(R.drawable.search_icon_unfocused),
                    focusedIcon = painterResource(R.drawable.search_icon_focused),
                    placeholderText = stringResource(R.string.enter_the_city_name),
                    value = searchQuery,
                    onValueChanged = onSearchValueChange,
                    contentScale = ContentScale.FillBounds
                )
            }
            LazyColumn(modifier = Modifier
                .fillMaxSize()) {
                if (defaultCityName.isNotBlank()) {
                    item { CityItem(name = defaultCityName, isDefault = true) }
                }
                items(searchResults) { cityName ->
                    if (cityName != defaultCityName) {
                        CityItem(
                            name = cityName,
                            isDefault = false,
                            modifier = Modifier
                                .clickable {
                                    onCityClick(cityName)
                                    onCitySelectionCallBack()
                                }
                        )
                    }
                }
            }
        }
    }
}
