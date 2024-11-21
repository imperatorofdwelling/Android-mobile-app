package com.imperatorofdwelling.android.presentation.ui.home_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.entities.dwelling.Adults
import com.imperatorofdwelling.android.presentation.entities.dwelling.Apartment
import com.imperatorofdwelling.android.presentation.entities.dwelling.Babies
import com.imperatorofdwelling.android.presentation.entities.dwelling.Children
import com.imperatorofdwelling.android.presentation.entities.dwelling.Hotel
import com.imperatorofdwelling.android.presentation.entities.dwelling.House
import com.imperatorofdwelling.android.presentation.entities.dwelling.Pets
import com.imperatorofdwelling.android.presentation.entities.dwelling.Rooms
import com.imperatorofdwelling.android.presentation.entities.dwelling.TypeOfDwelling
import com.imperatorofdwelling.android.presentation.ui.components.BackButton
import com.imperatorofdwelling.android.presentation.ui.components.MainCheckBox
import com.imperatorofdwelling.android.presentation.ui.components.PrimaryButton
import com.imperatorofdwelling.android.presentation.ui.components.text_fields.IconTextFieldTrailing
import com.imperatorofdwelling.android.presentation.ui.components.text_fields.TextFieldDefault
import com.imperatorofdwelling.android.presentation.ui.home_screen.components.CitySelection
import com.imperatorofdwelling.android.presentation.ui.home_screen.components.DwellingList
import com.imperatorofdwelling.android.presentation.ui.home_screen.components.RecentSearchList
import com.imperatorofdwelling.android.presentation.ui.home_screen.components.SelectionBlock
import com.imperatorofdwelling.android.presentation.ui.theme.GreyDividerColor
import com.imperatorofdwelling.android.presentation.ui.theme.extraLargeDp
import com.imperatorofdwelling.android.presentation.ui.theme.h3
import com.imperatorofdwelling.android.presentation.ui.theme.largeDp
import com.imperatorofdwelling.android.presentation.ui.theme.mediumDp
import com.imperatorofdwelling.android.presentation.ui.theme.title
import org.koin.androidx.compose.koinViewModel

class HomeScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = koinViewModel<HomeViewModel>()
        val screenState by screenModel.state.collectAsState()

        Scaffold(
            topBar = {
                HomeScreenTopBar(screenModel, screenState)
            },
            content = { paddingValues ->
                AnimatedVisibility(
                    visible = screenState.showCitySelection,
                    enter = slideInVertically(
                        initialOffsetY = { it },
                        animationSpec = tween(1000)
                    ),
                    exit = fadeOut(
                        animationSpec = tween(500),
                        targetAlpha = 0f
                    ) + slideOutVertically(
                        targetOffsetY = { -it },

                        animationSpec = tween(750)
                    )
                ) {
                    CitySelection(
                        modifier = Modifier.padding(paddingValues),
                        searchResults = screenState.searchResults,
                        defaultCity = screenState.defaultCity,
                        onCityClick = screenModel::setDefaultCity,
                    )
                }
                AnimatedVisibility(
                    visible = !screenState.showCitySelection,
                    enter = slideInVertically(
                        initialOffsetY = { it },
                        animationSpec = tween(1000)
                    ),
                    exit = fadeOut(
                        animationSpec = tween(500),
                        targetAlpha = 0f
                    ) + slideOutVertically(
                        targetOffsetY = { -it },
                        animationSpec = tween(500)
                    )
                ) {
                    HomeScreenBody(
                        modifier = Modifier.padding(paddingValues),
                        screenModel = screenModel,
                        screenState = screenState,
                        onSearchItemChanged = screenModel::updateSelectedTypes,
                        onDismissTypes = screenModel::onDismissTypes,
                        areTypesSelected = screenModel::areTypesSelect,
                        selectedTypesString = screenModel::selectedTypesString,
                        areResidentsSelected = screenModel::areResidentsSelect,
                        selectedResidentsString = screenModel::selectedResidentsString,
                        onDismissResidents = screenModel::onDismissResidents
                    )

                }
            }
        )
    }

    @Composable
    fun HomeScreenTopBar(
        viewModel: HomeViewModel,
        screenState: HomeViewModel.State
    ) {
        val backgroundNotificationBell = painterResource(R.drawable.notification_bell)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.background)
                .padding(horizontal = largeDp, vertical = mediumDp)
                .fillMaxWidth()
        ) {
            AnimatedVisibility(
                visible = screenState.showCitySelection,
                enter = slideInHorizontally(
                    initialOffsetX = { -300 },
                    animationSpec = tween(150)
                )
            ) {
                BackButton(onClick = { viewModel.updateShowCitySelection(false) })
            }
            IconTextFieldTrailing(
                modifier = Modifier.height(48.dp),
                placeholderText = if (screenState.defaultCity != null) {
                    screenState.defaultCity.name
                } else {
                    stringResource(id = R.string.enter_the_city_name)
                },
                trailingIcon = painterResource(id = R.drawable.two_sliders),
                onClickTrailing = {},
                value = screenState.searchQuery,
                onValueChanged = { newValue ->
                    viewModel.onSearchValueChange(newValue)
                    viewModel.updateShowCitySelection(true)
                },
                unfocusedIcon = painterResource(id = R.drawable.search_icon_unfocused),
                focusedIcon = painterResource(id = R.drawable.search_icon_focused),
                outFocus = screenState.showCitySelection
            )
            Spacer(modifier = Modifier.width(extraLargeDp))
            Image(
                painter = backgroundNotificationBell,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(largeDp))
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun HomeScreenBody(
        screenModel: HomeViewModel,
        screenState: HomeViewModel.State,
        onSearchItemChanged: (TypeOfDwelling) -> Unit,
        onDismissTypes: () -> Unit,
        onDismissResidents: () -> Unit,
        areTypesSelected: () -> Boolean,
        selectedTypesString: () -> String,
        areResidentsSelected: () -> Boolean,
        selectedResidentsString: () -> String,
        modifier: Modifier = Modifier
    ) {
        val scrollState = rememberScrollState()
        var showTypeDwellingSelect by remember { mutableStateOf(false) }
        val typeDwellingSelectState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

        var showNumberOfResidentsSelect by remember { mutableStateOf(false) }

        val numberOfResidentsState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        if (showNumberOfResidentsSelect) {
            ModalBottomSheet(
                onDismissRequest = {
                    showNumberOfResidentsSelect = false
                    onDismissResidents()
                },
                containerColor = Color.Black,
                sheetState = numberOfResidentsState
            ) {
                Column {
                    Spacer(modifier = Modifier.height(extraLargeDp))

                    Text(
                        text = stringResource(R.string.number_of_residents),
                        style = title,
                        modifier = Modifier.padding(start = extraLargeDp)
                    )

                    Spacer(modifier = Modifier.height(extraLargeDp))

                    ResidentsItem(
                        textName = stringResource(id = Adults.nameResource),
                        textCount = screenState.adultCount.toString(),
                        onPlusClick = {
                            Adults.increase()
                            screenModel.updateCounts()
                        },
                        onMinusClick = {
                            Adults.decrease()
                            screenModel.updateCounts()
                        }
                    )

                    ResidentsItem(
                        textName = stringResource(id = Rooms.nameResource),
                        textCount = screenState.roomsCount.toString(),
                        onPlusClick = {
                            Rooms.increase()
                            screenModel.updateCounts()
                        },
                        onMinusClick = {
                            Rooms.decrease()
                            screenModel.updateCounts()
                        }
                    )

                    ResidentsItem(
                        textName = stringResource(id = Children.nameResource),
                        textCount = screenState.childrenCount.toString(),
                        onPlusClick = {
                            Children.increase()
                            screenModel.updateCounts()
                        },
                        onMinusClick = {
                            Children.decrease()
                            screenModel.updateCounts()
                        }
                    )

                    ResidentsItem(
                        textName = stringResource(id = Babies.nameResource),
                        textCount = screenState.babiesCount.toString(),
                        onPlusClick = {
                            Babies.increase()
                            screenModel.updateCounts()
                        },
                        onMinusClick = {
                            Babies.decrease()
                            screenModel.updateCounts()
                        }
                    )

                    ResidentsItem(
                        textName = stringResource(id = Pets.nameResource),
                        textCount = screenState.petsCount.toString(),
                        onPlusClick = {
                            Pets.increase()
                            screenModel.updateCounts()
                        },
                        onMinusClick = {
                            Pets.decrease()
                            screenModel.updateCounts()
                        }
                    )

                    if (screenState.petsCount > 0) {
                        Row {
                            Spacer(modifier = Modifier.width(extraLargeDp))
                            TextFieldDefault(
                                value = "",
                                onValueChanged = {},
                                placeholderText = "What kind of pet",
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier.padding(end = 100.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(extraLargeDp))
                    }

                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 1.dp,
                        color = GreyDividerColor
                    )

                    Spacer(modifier = Modifier.fillMaxHeight(0.3f))

                    PrimaryButton(
                        text = stringResource(id = R.string.apply),
                        modifier = Modifier.padding(start = extraLargeDp, end = extraLargeDp),
                        onClick = {
                            showNumberOfResidentsSelect = false
                        }
                    )

                    Spacer(modifier = Modifier.height(extraLargeDp))

                }
            }
        }
        if (showTypeDwellingSelect) {
            ModalBottomSheet(
                onDismissRequest = {
                    showTypeDwellingSelect = false
                    onDismissTypes()
                },
                containerColor = Color.Black,
                sheetState = typeDwellingSelectState
            ) {
                Column {
                    Spacer(modifier = Modifier.height(extraLargeDp))

                    Text(
                        text = stringResource(R.string.type_of_dwelling),
                        style = title,
                        modifier = Modifier.padding(start = extraLargeDp)
                    )

                    Spacer(modifier = Modifier.height(extraLargeDp))

                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 1.dp,
                        color = GreyDividerColor
                    )

                    SelectTypeItem(
                        type = House,
                        onSearchItemChanged = onSearchItemChanged,
                        screenState = screenState
                    )

                    SelectTypeItem(
                        type = Apartment,
                        onSearchItemChanged = onSearchItemChanged,
                        screenState = screenState
                    )

                    SelectTypeItem(
                        type = Hotel,
                        onSearchItemChanged = onSearchItemChanged,
                        screenState = screenState
                    )

                    Spacer(modifier = Modifier.height(50.dp))

                    PrimaryButton(
                        text = stringResource(id = R.string.apply),
                        modifier = Modifier.padding(start = extraLargeDp, end = extraLargeDp),
                        onClick = {
                            showTypeDwellingSelect = false
                        }
                    )
                    Spacer(modifier = Modifier.height(extraLargeDp))
                }

            }
        }

        Column(
            modifier = modifier
                //.background()
                .verticalScroll(scrollState)
        ) {
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.SpaceBetween,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .background(color = DarkGrey)
//                    .padding(bottom = 16.dp, top = 16.dp)
//            ) {
//                Column(modifier = Modifier.padding(start = 24.dp)) {
//                    Text(text = stringResource(R.string.location), style = h5)
//                    Row(
//                        verticalAlignment = Alignment.CenterVertically,
//                        modifier = Modifier.clickable {
//                            navigator.push(
//                                CitySelectionScreen(
//                                    onCitySelectionCallBack = screenModel::updateDefaultCity
//                                )
//                            )
//                        }) {
//                        Text(
//                            text = screenState.defaultCity?.name
//                                ?: stringResource(R.string.anywhere), style = h2
//                        )
//                        Image(
//                            modifier = Modifier.padding(start = 1.dp, top = 3.dp),
//                            painter = painterResource(R.drawable.expend_button),
//                            contentDescription = null
//                        )
//                    }
//        }


            SelectionBlock(
                onClickTypeSelection = {
                    showTypeDwellingSelect = true
                },
                onClickResidentsSelection = {
                    showNumberOfResidentsSelect = true
                },
                areTypesSelected = areTypesSelected,
                selectedTypesString = selectedTypesString,
                areResidentsSelected = areResidentsSelected,
                selectedResidentsString = selectedResidentsString,
                showSelectionResidents = showNumberOfResidentsSelect,
                showSelectionTypes = showTypeDwellingSelect
            )

            RecentSearchList()

            DwellingList(title = stringResource(id = R.string.recent))
            DwellingList(title = stringResource(id = R.string.nearby))
            DwellingList(title = stringResource(id = R.string.featured))

        }
    }

    @Composable
    private fun ResidentsItem(
        onMinusClick: () -> Unit,
        onPlusClick: () -> Unit,
        textName: String,
        textCount: String
    ) {
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = GreyDividerColor
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = extraLargeDp,
                    end = extraLargeDp,
                    top = extraLargeDp,
                    bottom = extraLargeDp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Text(
                    text = textName,
                    style = h3
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.minus),
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        onMinusClick()
                    })

                Spacer(modifier = Modifier.width(largeDp))
                Text(text = textCount, style = h3)
                Spacer(modifier = Modifier.width(largeDp))

                Image(
                    painter = painterResource(id = R.drawable.plus),
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        onPlusClick()
                    })
            }
        }
    }


    @Composable
    private fun SelectTypeItem(
        type: TypeOfDwelling,
        onSearchItemChanged: (TypeOfDwelling) -> Unit,
        screenState: HomeViewModel.State
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = extraLargeDp,
                    end = extraLargeDp,
                    top = extraLargeDp,
                    bottom = extraLargeDp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Image(
                    painter = painterResource(id = type.iconDrawableId),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(mediumDp))
                Text(
                    text = stringResource(id = type.nameStringId),
                    style = h3
                )
            }
            var checked by remember { mutableStateOf(type in screenState.selectedTypes) }
            MainCheckBox(
                checked = checked,
                onCheckedChange = {
                    checked = !checked
                    onSearchItemChanged(type)
                }
            )
        }

        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = GreyDividerColor
        )
    }
}