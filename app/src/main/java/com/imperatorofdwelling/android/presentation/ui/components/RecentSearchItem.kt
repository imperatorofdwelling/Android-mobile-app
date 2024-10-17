package com.imperatorofdwelling.android.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.ui.theme.body
import com.imperatorofdwelling.android.presentation.ui.theme.h2

enum class RecentSearchItemState {
    CLOCK, HOME, HOTEL, APART
}

@Preview
@Composable
fun pref() {
    RecentSearch(
        RecentSearchItemState.HOME,
        stringResource(R.string.example_recent_search),
        stringResource(R.string.example_date),
        3
    )
}

@Composable
fun RecentSearch(
    state: RecentSearchItemState,
    searchString: String,
    date: String,
    countOfResidents: Int,
    modifier: Modifier = Modifier
) {
    val painter = when (state) {
        RecentSearchItemState.APART -> painterResource(R.drawable.apart)
        RecentSearchItemState.CLOCK -> painterResource(R.drawable.clock)
        RecentSearchItemState.HOME -> painterResource(R.drawable.home)
        RecentSearchItemState.HOTEL -> painterResource(R.drawable.hotel)
    }
    ConstraintLayout(
        modifier = modifier
    ) {
        val (backImage, icon, textBlock, imageCross) = createRefs()
        Image(
            painter = painterResource(R.drawable.recent_search_item),
            contentDescription = null,
            modifier = Modifier.constrainAs(backImage) {}
        )
        Image(painter = painter, contentDescription = null, modifier = Modifier.padding(start = 14.dp).constrainAs(icon){
            top.linkTo(backImage.top)
            bottom.linkTo(backImage.bottom)
        })
        Column(modifier = Modifier.padding(start = 16.dp).constrainAs(textBlock){
            start.linkTo(icon.end)
            top.linkTo(backImage.top)
            bottom.linkTo(backImage.bottom)
        }) {
            Text(text = searchString, style = h2)
            Text(text = date, style = body)
            Text(
                text = stringResource(R.string.count_residents, countOfResidents),
                style = body
            )
        }
        Image(
            painter = painterResource(R.drawable.cross),
            contentDescription = null,
            modifier = Modifier.padding(end = 16.dp).constrainAs(imageCross){
                end.linkTo(backImage.end)
                top.linkTo(backImage.top)
                bottom.linkTo(backImage.bottom)
            }
        )
    }

}