package com.imperatorofdwelling.android.presentation.ui.home_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.ModalBottomSheet
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
import cafe.adriel.voyager.hilt.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.entities.dwelling.Adult
import com.imperatorofdwelling.android.presentation.entities.dwelling.Apartment
import com.imperatorofdwelling.android.presentation.entities.dwelling.Babies
import com.imperatorofdwelling.android.presentation.entities.dwelling.Children
import com.imperatorofdwelling.android.presentation.entities.dwelling.Hotel
import com.imperatorofdwelling.android.presentation.entities.dwelling.House
import com.imperatorofdwelling.android.presentation.entities.dwelling.Pets
import com.imperatorofdwelling.android.presentation.entities.dwelling.Rooms
import com.imperatorofdwelling.android.presentation.entities.dwelling.TypeOfDwelling
import com.imperatorofdwelling.android.presentation.ui.city_selection.CitySelectionScreen
import com.imperatorofdwelling.android.presentation.ui.components.DefaultButton
import com.imperatorofdwelling.android.presentation.ui.components.DefaultButtonState
import com.imperatorofdwelling.android.presentation.ui.components.MainCheckBox
import com.imperatorofdwelling.android.presentation.ui.components.PrimaryButton
import com.imperatorofdwelling.android.presentation.ui.components.TextFieldDefault
import com.imperatorofdwelling.android.presentation.ui.home_screen.components.DwellingList
import com.imperatorofdwelling.android.presentation.ui.home_screen.components.RecentSearchList
import com.imperatorofdwelling.android.presentation.ui.theme.Black
import com.imperatorofdwelling.android.presentation.ui.theme.DarkGrey
import com.imperatorofdwelling.android.presentation.ui.theme.GreyDividerColor
import com.imperatorofdwelling.android.presentation.ui.theme.extraLargeDp
import com.imperatorofdwelling.android.presentation.ui.theme.h2
import com.imperatorofdwelling.android.presentation.ui.theme.h3
import com.imperatorofdwelling.android.presentation.ui.theme.h4_grey
import com.imperatorofdwelling.android.presentation.ui.theme.h5
import com.imperatorofdwelling.android.presentation.ui.theme.largeDp
import com.imperatorofdwelling.android.presentation.ui.theme.mediumDp
import com.imperatorofdwelling.android.presentation.ui.theme.title

class HomeScreen : Screen {

    @Composable
    override fun Content() {
        HomeScreenBody()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun HomeScreenBody() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = getScreenModel<HomeScreenModel, HomeScreenModel.Factory> { factory ->
            factory.create(HomeScreenModel::class.java)
        }
        val backgroundNotificationBell = painterResource(R.drawable.notification_bell)
        val scrollState = rememberScrollState()
        val screenState by screenModel.state.collectAsState()
        var showTypeDwellingSelect by remember { mutableStateOf(false) }
        val typeDwellingSelectState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

        var showNumberOfResidentsSelect by remember { mutableStateOf(false) }

        val numberOfResidentsState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        if (showNumberOfResidentsSelect) {
            ModalBottomSheet(
                onDismissRequest = { showNumberOfResidentsSelect = false },
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
                        textName = stringResource(id = Adult.nameResource),
                        textCount = screenState.adultCount.toString(),
                        onPlusClick = {
                            Adult.increase()
                            screenModel.updateCounts()
                        },
                        onMinusClick = {
                            Adult.decrease()
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
                        onClick = {}
                    )


                    Spacer(modifier = Modifier.height(extraLargeDp))

                }
            }
        }
        if (showTypeDwellingSelect) {
            ModalBottomSheet(
                onDismissRequest = { showTypeDwellingSelect = false },
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

                    SelectTypeItem(type = House())
                    SelectTypeItem(type = Apartment())
                    SelectTypeItem(type = Hotel())

                    Spacer(modifier = Modifier.height(50.dp))

                    PrimaryButton(
                        text = stringResource(id = R.string.apply),
                        modifier = Modifier.padding(start = extraLargeDp, end = extraLargeDp),
                        onClick = {}
                    )
                    Spacer(modifier = Modifier.height(extraLargeDp))
                }

            }
        }

        Column(
            modifier = Modifier
                .background(Black)
                .verticalScroll(scrollState)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = DarkGrey)
                    .padding(bottom = 16.dp, top = 16.dp)
            ) {
                Column(modifier = Modifier.padding(start = 24.dp)) {
                    Text(text = stringResource(R.string.location), style = h5)
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable {
                            navigator.push(CitySelectionScreen(
                                onCitySelectedCallBack = {
                                    screenModel.updateDefaultCity()
                                }
                            ))
                        }) {
                        Text(
                            text = screenState.defaultCity?.name
                                ?: stringResource(R.string.anywhere), style = h2
                        )
                        Image(
                            modifier = Modifier.padding(start = 1.dp, top = 3.dp),
                            painter = painterResource(R.drawable.expend_button),
                            contentDescription = null
                        )
                    }
                }

                Image(
                    modifier = Modifier.padding(end = 24.dp),
                    painter = backgroundNotificationBell,
                    contentDescription = null
                )
            }

            SelectionBlock(
                onClickTypeSelection = {
                    showTypeDwellingSelect = true
                },
                onClickResidentsSelection = {
                    showNumberOfResidentsSelect = true
                }
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
    private fun SelectTypeItem(type: TypeOfDwelling) {
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

            MainCheckBox(
                agreedToTerms = false,
                onAgreedToTermsChange = {},
                modifier = Modifier
            )

        }

        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = GreyDividerColor
        )
    }


    @Composable
    private fun SelectionBlock(
        onClickTypeSelection: () -> Unit,
        onClickResidentsSelection: () -> Unit
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, top = 16.dp)
        ) {
            Box(contentAlignment = Alignment.CenterStart, modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onClickTypeSelection()
                }) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter),
                    painter = painterResource(R.drawable.type_of_dwelling),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier.padding(start = 50.dp),
                    text = stringResource(R.string.type_of_dwelling_you_need),
                    style = h4_grey
                )
            }
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter),
                    painter = painterResource(R.drawable.dates),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier.padding(start = 50.dp),
                    text = stringResource(R.string.dates),
                    style = h4_grey
                )
            }
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .clickable {
                        onClickResidentsSelection()
                    }
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter),
                    painter = painterResource(R.drawable.residents),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier.padding(start = 50.dp),
                    text = stringResource(R.string.residents),
                    style = h4_grey
                )
            }
            DefaultButton(
                text = stringResource(R.string.apply),
                state = DefaultButtonState.DEFAULT,
                modifier = Modifier.padding(top = 8.dp),
                onCLick = {}
            )
        }
    }
}