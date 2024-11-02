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
import com.imperatorofdwelling.android.presentation.ui.components.RecentSearch
import com.imperatorofdwelling.android.presentation.ui.components.RecentSearchItemState
import com.imperatorofdwelling.android.presentation.ui.theme.h4_accent
import com.imperatorofdwelling.android.presentation.ui.theme.title


@Composable
fun RecentSearchList(){
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
}