package com.imperatorofdwelling.android.presentation.ui.home_screen.filtration

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.entities.dwelling.Apartment
import com.imperatorofdwelling.android.presentation.entities.dwelling.Hotel
import com.imperatorofdwelling.android.presentation.entities.dwelling.House
import com.imperatorofdwelling.android.presentation.ui.components.buttons.BackButton
import com.imperatorofdwelling.android.presentation.ui.components.text_fields.PrimaryTextField
import com.imperatorofdwelling.android.presentation.ui.home_screen.components.Plate
import com.imperatorofdwelling.android.presentation.ui.theme.DarkGrey
import com.imperatorofdwelling.android.presentation.ui.theme.GreyStroke
import com.imperatorofdwelling.android.presentation.ui.theme.White
import com.imperatorofdwelling.android.presentation.ui.theme.extraLargeDp
import com.imperatorofdwelling.android.presentation.ui.theme.forButtons16dp
import com.imperatorofdwelling.android.presentation.ui.theme.h2
import com.imperatorofdwelling.android.presentation.ui.theme.h4_white
import com.imperatorofdwelling.android.presentation.ui.theme.largeDp
import com.imperatorofdwelling.android.presentation.ui.theme.smallDp
import org.koin.androidx.compose.koinViewModel

class FiltrationScreen : Screen {
    @Composable
    @Preview
    override fun Content() {
        val viewModel = koinViewModel<FiltrationViewModel>()
        val state = viewModel.state.collectAsState()
        Scaffold(
            topBar = { FiltrationTopBar() }
        ) { paddingValues ->
            FiltrationBody(
                numberOfRoomsList = state.value.numberOfRoomsList,
                fromValue = state.value.fromValue,
                onFromValueChange = viewModel::onFromValueChange,
                toValue = state.value.toValue,
                onToValueChange = viewModel::onToValueChange,
                modifier = Modifier.padding(paddingValues)
            )
        }
    }

    @Composable
    private fun FiltrationBody(
        numberOfRoomsList: List<String>,
        fromValue: String,
        onFromValueChange: (String) -> Unit,
        toValue: String,
        onToValueChange: (String) -> Unit,
        modifier: Modifier = Modifier,
    ) {
        val focusManager = LocalFocusManager.current
        LazyColumn(
            modifier = modifier
                .padding(largeDp)
                .clickable { focusManager.clearFocus() },
        ) {
            item {
                HorizontalDivider(thickness = 0.5.dp, color = GreyStroke)
                Spacer(modifier = Modifier.height(largeDp))
                Text(text = stringResource(R.string.type_of_accommodation), style = h2)
                Spacer(modifier = Modifier.height(extraLargeDp))
            }
            item {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Plate(isActive = false, modifier = Modifier.size(100.dp)) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                painter = painterResource(id = House.iconDrawableId),
                                contentDescription = stringResource(id = House.nameStringId),
                                tint = White
                            )
                            Spacer(modifier = Modifier.height(smallDp))
                            Text(style = h4_white, text = stringResource(id = House.nameStringId))
                        }
                    }
                    Plate(isActive = false, modifier = Modifier.size(100.dp)) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                painter = painterResource(id = Apartment.iconDrawableId),
                                contentDescription = stringResource(id = Apartment.nameStringId),
                                tint = White
                            )
                            Spacer(modifier = Modifier.height(smallDp))
                            Text(
                                style = h4_white,
                                text = stringResource(id = Apartment.nameStringId)
                            )
                        }
                    }
                    Plate(isActive = false, modifier = Modifier.size(100.dp)) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                painter = painterResource(id = Hotel.iconDrawableId),
                                contentDescription = stringResource(id = Hotel.nameStringId),
                                tint = White
                            )
                            Spacer(modifier = Modifier.height(smallDp))
                            Text(style = h4_white, text = stringResource(id = Hotel.nameStringId))
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(largeDp))
                HorizontalDivider(thickness = 0.5.dp, color = GreyStroke)
                Spacer(modifier = Modifier.height(largeDp))
                Text(text = stringResource(R.string.price_range), style = h2)
                Spacer(modifier = Modifier.height(extraLargeDp))
            }
            item {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    PrimaryTextField(
                        value = fromValue,
                        modifier = Modifier.weight(1f),
                        onValueChange = onFromValueChange,
                        hint = stringResource(R.string.from)
                    )
                    Spacer(modifier = Modifier.width(smallDp))
                    PrimaryTextField(
                        value = toValue,
                        modifier = Modifier.weight(1f),
                        onValueChange = onToValueChange,
                        hint = stringResource(R.string.to)
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(largeDp))
                HorizontalDivider(thickness = 0.5.dp, color = GreyStroke)
                Spacer(modifier = Modifier.height(largeDp))
                Text(text = stringResource(R.string.number_of_rooms), style = h2)
                Spacer(modifier = Modifier.height(extraLargeDp))
            }
            item {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    for (roomCount in numberOfRoomsList) {
                        Plate(
                            isActive = false,
                            modifier = Modifier.size(44.dp)
                        ) {
                            Text(style = h4_white, text = roomCount)
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun FiltrationTopBar() {

        val navigator = LocalNavigator.currentOrThrow
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = DarkGrey),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BackButton(
                onClick = { navigator.pop() },
                modifier = Modifier.padding(vertical = 10.dp, horizontal = 16.dp)
            )
            Text(style = h2, text = stringResource(R.string.filter))
            Text(
                style = forButtons16dp,
                text = stringResource(R.string.reset_all),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}