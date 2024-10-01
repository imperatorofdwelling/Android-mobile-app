package com.imperatorofdwelling.android.presentation.ui.home_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.entities.Currency
import com.imperatorofdwelling.android.presentation.entities.Period
import com.imperatorofdwelling.android.presentation.entities.Price
import com.imperatorofdwelling.android.presentation.ui.components.DefaultButton
import com.imperatorofdwelling.android.presentation.ui.components.DefaultButtonState
import com.imperatorofdwelling.android.presentation.ui.components.DwellingItem
import com.imperatorofdwelling.android.presentation.ui.components.RecentSearch
import com.imperatorofdwelling.android.presentation.ui.components.RecentSearchItemState
import com.imperatorofdwelling.android.presentation.ui.theme.Black
import com.imperatorofdwelling.android.presentation.ui.theme.DarkGrey
import com.imperatorofdwelling.android.presentation.ui.theme.h2
import com.imperatorofdwelling.android.presentation.ui.theme.h4_accent
import com.imperatorofdwelling.android.presentation.ui.theme.h4_grey
import com.imperatorofdwelling.android.presentation.ui.theme.h5
import com.imperatorofdwelling.android.presentation.ui.theme.title

@Preview
@Composable
fun HomeScreen() {

    //val backgroundTextField = painterResource(R.drawable.search_bar_hotel_hold)
    val backgroundNotificationBell = painterResource(R.drawable.notification_bell)
    val scrollState = rememberScrollState()
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
                Row(verticalAlignment = Alignment.CenterVertically) {
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
                painter = backgroundNotificationBell,
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
                    val startPadding = if(index == 0) 24.dp else 0.dp
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
                    val startPadding = if(index == 0) 24.dp else 0.dp
                    DwellingItem(
                        painterResource(R.drawable.example_hotel_image),
                        liked = false,
                        price = Price(Currency.US, 120, Period.Nightly),
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
                    val startPadding = if(index == 0) 24.dp else 0.dp
                    DwellingItem(
                        painterResource(R.drawable.example_hotel_image),
                        liked = false,
                        price = Price(Currency.US, 120, Period.Nightly),
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
                    val startPadding = if(index == 0) 24.dp else 0.dp
                    DwellingItem(
                        painterResource(R.drawable.example_hotel_image),
                        liked = false,
                        price = Price(Currency.US, 120, Period.Nightly),
                        modifier = Modifier
                            .fillParentMaxWidth(0.85f)
                            .padding(start = startPadding, end = 10.dp)
                    )
                }
            }
        }
    }
}


@Composable
private fun SelectionBlock() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, top = 16.dp)
    ) {
        Box(contentAlignment = Alignment.CenterStart, modifier = Modifier.fillMaxWidth()) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                painter = painterResource(R.drawable.type_of_dwelling),
                contentDescription = null
            )
            Text(
                modifier = Modifier.padding(start = 50.dp),
                text = stringResource(R.string.type_of_dweeling_you_need),
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

