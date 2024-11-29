package com.imperatorofdwelling.android.presentation.ui.apart_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imperatorofdwelling.android.presentation.entities.dwelling.Hotel
import com.imperatorofdwelling.android.presentation.entities.dwelling.TypeOfDwelling
import com.imperatorofdwelling.android.presentation.ui.theme.Accent
import com.imperatorofdwelling.android.presentation.ui.theme.White
import com.imperatorofdwelling.android.presentation.ui.theme.setAlpha

@Composable
@Preview
fun MapPointPreview() {
    MapPoint(Hotel)
}

@Composable
fun MapPoint(
    dwellingType: TypeOfDwelling
) {
    Box(
        modifier = Modifier
            .drawBehind {
                val borderWidth = 2.dp.toPx()
                val radius = size.minDimension / 2 + borderWidth / 2

                drawCircle(
                    color = Accent.setAlpha(0.5f),
                    radius = radius,
                    style = Stroke(width = borderWidth)
                )
            }
            .clip(CircleShape)
            .size(48.dp)
            .background(color = Accent),

        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(16.dp),
            painter = painterResource(id = dwellingType.iconDrawableId),
            contentDescription = stringResource(id = dwellingType.nameStringId),
            tint = White
        )
    }
}