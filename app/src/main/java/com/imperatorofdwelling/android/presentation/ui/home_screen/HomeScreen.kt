package com.imperatorofdwelling.android.presentation.ui.home_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.entities.Dollar
import com.imperatorofdwelling.android.presentation.entities.Period
import com.imperatorofdwelling.android.presentation.entities.Price
import com.imperatorofdwelling.android.presentation.ui.city_selection.CitySelectionScreen
import com.imperatorofdwelling.android.presentation.ui.components.DwellingItem
import com.imperatorofdwelling.android.presentation.ui.components.RecentSearch
import com.imperatorofdwelling.android.presentation.ui.components.RecentSearchItemState
import com.imperatorofdwelling.android.presentation.ui.home_screen.components.SelectionBlock
import com.imperatorofdwelling.android.presentation.ui.theme.Black
import com.imperatorofdwelling.android.presentation.ui.theme.DarkGrey
import com.imperatorofdwelling.android.presentation.ui.theme.h2
import com.imperatorofdwelling.android.presentation.ui.theme.h4_accent
import com.imperatorofdwelling.android.presentation.ui.theme.h5
import com.imperatorofdwelling.android.presentation.ui.theme.title
import org.koin.androidx.compose.koinViewModel



class HomeScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val viewModel = koinViewModel<HomeViewModel>()
        val state = viewModel.state.collectAsState()

        HomeScreenBody(
            onSelectCityClick = { navigator.push(CitySelectionScreen()) }
        )
    }

    @Composable
    fun HomeScreenBody(
        onSelectCityClick: () -> Unit = {}
    ) {
        Column(
            modifier = Modifier
                .background(Black)
                .verticalScroll(rememberScrollState())

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
                        modifier = Modifier.clickable { onSelectCityClick() }
                    ) {
                        Text(text = stringResource(R.string.example_city), style = h2)

                        Image(
                            modifier = Modifier.padding(start = 1.dp, top = 3.dp),
                            painter = painterResource(R.drawable.expend_button),
                            contentDescription = null
                        )
                    }
                }

                Image(
                    modifier = Modifier.padding(end = 24.dp),
                    painter = painterResource(R.drawable.notification_bell),
                    contentDescription = null
                )
            }

            SelectionBlock()

            Column(modifier = Modifier.padding(top = 24.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = stringResource(R.string.your_recent_search), style = title)
                    Text(text = stringResource(R.string.clear_all), style = h4_accent)
                }
                LazyRow(
                    modifier = Modifier
                        .padding(top = 12.dp)
                ) {
                    items(3) { index ->
                        val startPadding = if (index == 0) 24.dp else 0.dp
                        RecentSearch(
                            RecentSearchItemState.APART,
                            stringResource(R.string.example_recent_search),
                            stringResource(R.string.example_date),
                            3,
                            modifier = Modifier.padding(start = startPadding, end = 10.dp)
                        )
                    }
                }
            }
            Column(modifier = Modifier.padding(top = 24.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = stringResource(R.string.recent), style = title)
                    Text(text = stringResource(R.string.see_all), style = h4_accent)
                }

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                ) {
                    items(3) { index ->
                        val startPadding = if (index == 0) 24.dp else 0.dp
                        DwellingItem(
                            painterResource(R.drawable.example_hotel_image),
                            liked = false,
                            price = Price(Dollar(), 120, Period.Nightly),
                            modifier = Modifier
                                .fillParentMaxWidth(0.85f)
                                .padding(start = startPadding, end = 10.dp)
                        )
                    }
                }
            }
            Column(modifier = Modifier.padding(top = 24.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = stringResource(R.string.nearby), style = title)
                    Text(text = stringResource(R.string.see_all), style = h4_accent)
                }

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                ) {
                    items(3) { index ->
                        val startPadding = if (index == 0) 24.dp else 0.dp
                        DwellingItem(
                            painterResource(R.drawable.example_hotel_image),
                            liked = false,
                            price = Price(Dollar(), 120, Period.Nightly),
                            modifier = Modifier
                                .fillParentMaxWidth(0.85f)
                                .padding(start = startPadding, end = 10.dp)
                        )
                    }
                }
            }
            Column(modifier = Modifier.padding(top = 24.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = stringResource(R.string.featured), style = title)
                    Text(text = stringResource(R.string.see_all), style = h4_accent)
                }

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                ) {
                    items(3) { index ->
                        val startPadding = if (index == 0) 24.dp else 0.dp
                        DwellingItem(
                            painterResource(R.drawable.example_hotel_image),
                            liked = false,
                            price = Price(Dollar(), 120, Period.Nightly),
                            modifier = Modifier
                                .fillParentMaxWidth(0.85f)
                                .padding(start = startPadding, end = 10.dp)
                        )
                    }
                }
            }

        }
    }
}