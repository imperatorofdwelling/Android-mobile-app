package com.imperatorofdwelling.android.presentation.ui.city_selection

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.entities.cities.CityViewModelEntity
import com.imperatorofdwelling.android.presentation.ui.components.IconTextField
import com.imperatorofdwelling.android.presentation.ui.components.SmallSpacer
import com.imperatorofdwelling.android.presentation.ui.theme.extraLargeDp
import com.imperatorofdwelling.android.presentation.ui.theme.h4_white
import com.imperatorofdwelling.android.presentation.ui.theme.largeDp


class CitySelectionScreen(
    private val onCitySelectedCallBack: () -> Unit
) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel =
            getScreenModel<CitySelectionScreenModel, CitySelectionScreenModel.Factory> { factory ->
                factory.create(CitySelectionScreenModel::class.java)
            }
        CitySelectionBody(
            onBackClick = {
                onCitySelectedCallBack()
                navigator.pop()
            },
            screenModel = screenModel
        )
    }


    @Composable
    fun CityItem(
        item: CityViewModelEntity,
        isDefault: Boolean,
        modifier: Modifier = Modifier
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = largeDp, bottom = largeDp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Spacer(modifier = Modifier.width(largeDp))
            Image(
                modifier = Modifier.height(32.dp),
                painter = painterResource(id = R.drawable.location_label),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(largeDp))
            Text(item.name, style = h4_white, modifier = Modifier.weight(1f))
            if (isDefault) {
                Image(painterResource(id = R.drawable.select_mark), contentDescription = null)
                Spacer(modifier = Modifier.width(largeDp))
            }
        }
    }


    @Composable
    fun CitySelectionBody(
        onBackClick: () -> Unit,
        screenModel: CitySelectionScreenModel
    ) {
        val screenState by screenModel.state.collectAsState()

        Column(modifier = Modifier.fillMaxSize()) {
            SmallSpacer()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                verticalAlignment = Alignment.Top,
            ) {
                Image(
                    modifier = Modifier
                        .padding(start = extraLargeDp)
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
                val searchQuery = remember { mutableStateOf("") }

                IconTextField(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 11.dp, end = extraLargeDp),
                    unfocusedIcon = painterResource(R.drawable.search_icon_unfocused),
                    focusedIcon = painterResource(R.drawable.search_icon_focused),
                    placeholderText = stringResource(R.string.enter_the_city_name),
                    value = searchQuery.value,
                    onValueChanged = { searchValue ->

                        searchQuery.value = searchValue

                        screenModel.searchCity(searchValue)

                    },
                    contentScale = ContentScale.FillBounds
                )
            }
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                if (screenState.defaultCity != null) {
                    item { CityItem(item = screenState.defaultCity!!, isDefault = true) }
                }
                items(screenState.searchResults) { item ->
                    if (screenState.defaultCity == null || item != screenState.defaultCity) {
                        CityItem(
                            item = CityViewModelEntity(item.name),
                            isDefault = false,
                            modifier = Modifier.clickable {
                                screenModel.setDefaultCity(item)
                            })
                    }
                }
            }
        }


    }
}
