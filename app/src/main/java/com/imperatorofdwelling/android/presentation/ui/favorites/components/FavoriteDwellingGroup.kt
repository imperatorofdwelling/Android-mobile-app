package com.imperatorofdwelling.android.presentation.ui.favorites.components

import android.graphics.drawable.shapes.ArcShape
import android.graphics.drawable.shapes.OvalShape
import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.ui.theme.DarkGrey
import com.imperatorofdwelling.android.presentation.ui.theme.extraLargeDp
import com.imperatorofdwelling.android.presentation.ui.theme.h4_white
import com.imperatorofdwelling.android.presentation.ui.theme.largeDp
import com.imperatorofdwelling.android.presentation.ui.theme.smallDp

@Composable
@Preview
fun FavoriteDwellingPreview() {
    FavoriteDwellingGroup(
        groupName = stringResource(id = R.string.example_city),
        countOption = 3,
        onActionClick = {}
    )
}

@Composable
fun FavoriteDwellingGroup(
    groupName: String,
    countOption: Int,
    modifier: Modifier = Modifier,
    onActionClick: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth().background(
            color = DarkGrey,
            shape = RoundedCornerShape(largeDp)
        ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = extraLargeDp)
        ) {
            Spacer(Modifier.width(largeDp))
            Image(painterResource(id = R.drawable.star_white), contentDescription = null)
            Spacer(Modifier.width(largeDp))
            Column {
                Text(text = groupName, style = h4_white)
                Spacer(modifier = Modifier.height(smallDp))
                Text(text = "$countOption " + stringResource(R.string.options), style = h4_white)
            }
        }
        Row {
            Image(
                painterResource(id = R.drawable.action_menu),
                contentDescription = null,
                modifier = Modifier.clickable {
                    onActionClick()
                }
            )
            Spacer(Modifier.width(largeDp))
        }

    }
}