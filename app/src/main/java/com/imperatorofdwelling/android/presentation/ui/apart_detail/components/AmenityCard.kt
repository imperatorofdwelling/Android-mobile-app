package com.imperatorofdwelling.android.presentation.ui.apart_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imperatorofdwelling.android.presentation.entities.dwelling.Amenity
import com.imperatorofdwelling.android.presentation.entities.dwelling.WiFiAmenity
import com.imperatorofdwelling.android.presentation.ui.theme.GreyStroke
import com.imperatorofdwelling.android.presentation.ui.theme.SecondGreyStroke
import com.imperatorofdwelling.android.presentation.ui.theme.White
import com.imperatorofdwelling.android.presentation.ui.theme.h5
import com.imperatorofdwelling.android.presentation.ui.theme.largeDp
import com.imperatorofdwelling.android.presentation.ui.theme.mediumDp
import com.imperatorofdwelling.android.presentation.ui.theme.smallDp


@Composable
@Preview
fun AmenityPreview() {
    Amenity(WiFiAmenity())
}

@Composable
fun Amenity(
    amenity: Amenity,

    modifier: Modifier = Modifier
) {
    val amenityName: String = if (amenity.hasCount()) {
        StringBuilder()
            .append(amenity.count)
            .append(' ')
            .append(
                stringResource(id = amenity.nameStringId)
            )
            .toString()
    } else {
        stringResource(id = amenity.nameStringId)
    }

    Column(
        modifier = modifier
            .background(
                color = SecondGreyStroke,
                shape = RoundedCornerShape(smallDp)
            )
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(smallDp),
                color = GreyStroke
            )
            .padding(
                start = largeDp,
                end = largeDp
            )
    ) {
        Spacer(
            modifier = Modifier.height(largeDp)
        )
        Icon(
            modifier = Modifier,
            painter = painterResource(id = amenity.iconDrawableId),
            contentDescription = stringResource(id = amenity.nameStringId),
            tint = White
        )
        Spacer(
            modifier = Modifier.height(mediumDp)
        )
        Text(
            text = amenityName,
            style = h5,
            color = White
        )
        Spacer(
            modifier.height(largeDp)
        )
    }
}