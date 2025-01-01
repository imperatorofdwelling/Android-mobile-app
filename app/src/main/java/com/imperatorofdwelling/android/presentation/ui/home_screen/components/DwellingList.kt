package com.imperatorofdwelling.android.presentation.ui.home_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.entities.Dwelling
import com.imperatorofdwelling.android.presentation.ui.apart_detail.ApartDetail
import com.imperatorofdwelling.android.presentation.ui.components.DwellingItem
import com.imperatorofdwelling.android.presentation.ui.theme.extraLargeDp
import com.imperatorofdwelling.android.presentation.ui.theme.h4_accent
import com.imperatorofdwelling.android.presentation.ui.theme.largeDp
import com.imperatorofdwelling.android.presentation.ui.theme.mediumDp

@Composable
fun DwellingListColumn(
    dwellingList: List<Dwelling>,
    modifier: Modifier = Modifier,
    title: String? = null,
    onLikeItemClick: suspend (String, Boolean) -> Boolean
) {
    val navigator = LocalNavigator.currentOrThrow
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = extraLargeDp)
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title ?: "",
                    style = com.imperatorofdwelling.android.presentation.ui.theme.title
                )
                Text(text = stringResource(R.string.see_all), style = h4_accent)
            }
        }
        items(
            dwellingList,
            key = { item -> "${item.id}${item.isLiked}" }) { item ->
            DwellingItem(
                item,
                modifier = Modifier
                    .clickable {
                        navigator.push(ApartDetail(item))
                    },
                onLikeClick = onLikeItemClick
            )
        }
    }
}


@Composable
fun DwellingListRow(
    dwellingList: List<Dwelling>,
    modifier: Modifier = Modifier,
    title: String? = null,
    onLikeItemClick: suspend (String, Boolean) -> Boolean
) {
    val navigator = LocalNavigator.currentOrThrow
    Column(
        modifier = modifier
            .padding(top = extraLargeDp)
            .height(268.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = extraLargeDp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title ?: "",
                style = com.imperatorofdwelling.android.presentation.ui.theme.title
            )
            Text(text = stringResource(R.string.see_all), style = h4_accent)
        }

        val lazyListState = rememberLazyListState()
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            state = lazyListState,
            flingBehavior = rememberSnapFlingBehavior(
                lazyListState = lazyListState,
                snapPosition = SnapPosition.Center
            )
        ) {
            itemsIndexed(
                items = dwellingList,
                key = { index, item -> "${item.id}${item.isLiked}" }) { index, item ->
                if (index == 0) Spacer(modifier = Modifier.width(largeDp))
                DwellingItem(
                    item,
                    modifier = Modifier
                        .fillParentMaxWidth(0.85f)
                        .clickable {
                            navigator.push(ApartDetail(item))
                        },
                    imageModifier = Modifier.height(150.dp),
                    onLikeClick = onLikeItemClick
                )
                if (index == dwellingList.size - 1) {
                    Spacer(modifier = Modifier.width(largeDp))
                } else {
                    Spacer(modifier = Modifier.width(mediumDp))
                }
            }
        }
    }
}
