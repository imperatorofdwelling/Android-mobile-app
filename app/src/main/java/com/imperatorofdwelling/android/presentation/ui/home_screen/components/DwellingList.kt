package com.imperatorofdwelling.android.presentation.ui.home_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.entities.Dwelling
import com.imperatorofdwelling.android.presentation.entities.Euro
import com.imperatorofdwelling.android.presentation.entities.Period
import com.imperatorofdwelling.android.presentation.entities.Price
import com.imperatorofdwelling.android.presentation.ui.components.DwellingItem
import com.imperatorofdwelling.android.presentation.ui.theme.extraLargeDp
import com.imperatorofdwelling.android.presentation.ui.theme.h4_accent
import com.imperatorofdwelling.android.presentation.ui.theme.mediumDp

@Composable
fun DwellingList(
    modifier: Modifier = Modifier,
    title: String? = null,
    items: List<Dwelling>? = null
) {
    Column(modifier = modifier.padding(top = extraLargeDp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = extraLargeDp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title?: "",
                style = com.imperatorofdwelling.android.presentation.ui.theme.title
            )
            Text(text = stringResource(R.string.see_all), style = h4_accent)
        }

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
        ) {
            items(3) { index ->
                val startPadding = if (index == 0) extraLargeDp else 0.dp
                DwellingItem(
                    Dwelling(
                        stringResource(id = R.string.example_name_hotel),
                        stringResource(id = R.string.example_address),
                        Price(Euro(), 40, Period.Daily),
                        mark = stringResource(id = R.string.example_mark).toDouble(),
                        isLiked = false,
                        imageRes = R.drawable.example_hotel_image
                    ),
                    modifier = Modifier.fillParentMaxWidth(0.85f)
                        .padding(start = startPadding, end = mediumDp)
                )
            }
        }
    }
}
