package com.imperatorofdwelling.android.presentation.ui.landlord.my_objects.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.data.entities.ToStayData
import com.imperatorofdwelling.android.domain.stays.entities.Stay
import com.imperatorofdwelling.android.presentation.entities.Dwelling
import com.imperatorofdwelling.android.presentation.ui.theme.DarkGrey
import com.imperatorofdwelling.android.presentation.ui.theme.Grey1
import com.imperatorofdwelling.android.presentation.ui.theme.GreyStroke
import com.imperatorofdwelling.android.presentation.ui.theme.White
import com.imperatorofdwelling.android.presentation.ui.theme.h4_grey


@Composable
fun Object(
    modifier: Modifier = Modifier,
    _object: Stay
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .requiredWidth(width = 361.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(color = DarkGrey)
            .border(
                border = BorderStroke(1.dp, GreyStroke), shape = RoundedCornerShape(8.dp)
            )
            .padding(all = 16.dp)
    ) {
        Box(
            modifier = Modifier.requiredSize(size = 96.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.example_hotel_image),
                contentDescription = "Photo",
                modifier = Modifier
                    .padding(start = 16.dp)
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = (-38).dp, y = 0.dp
                    )
                    .requiredWidth(width = 144.dp)
                    .requiredHeight(height = 96.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.Bottom),
            horizontalAlignment = Alignment.End,
            modifier = Modifier
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(2.dp, Alignment.Top),
                    modifier = Modifier.weight(weight = 1f)
                ) {
                    Text(
                        text = _object.name,
                        color = White,
                        lineHeight = 1.36.em,
                        style = TextStyle(
                            fontSize = 14.sp
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.point),
                            contentDescription = "Icons",
                            colorFilter = ColorFilter.tint(Grey1),
                            modifier = Modifier.requiredSize(size = 16.dp)
                        )
                        Text(
                            text = _object.street + ' ' + _object.house,
                            color = Grey1,
                            lineHeight = 1.36.em,
                            style = h4_grey,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.star),
                            contentDescription = "Icons",
                            colorFilter = ColorFilter.tint(Grey1),
                            modifier = Modifier.requiredSize(size = 16.dp)
                        )
                        Text(
                            text = _object.rating.toString(), color = Grey1, lineHeight = 1.36.em, style = h4_grey
                        )
                    }
                    Text(
                        text = _object.price.toString(),
                        color = White,
                        lineHeight = 1.36.em,
                        style = h4_grey,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Icon(
                    painter = painterResource(id = R.drawable.edit),
                    contentDescription = "Icons",
                    tint = White
                )
            }
            Text(
                text = _object.createdAt ?: "",
                color = Grey1,
                lineHeight = 1.36.em,
                style = h4_grey,
                modifier = Modifier
                    .requiredWidth(width = 109.dp)
                    .requiredHeight(height = 19.dp)
            )
        }
    }
}
