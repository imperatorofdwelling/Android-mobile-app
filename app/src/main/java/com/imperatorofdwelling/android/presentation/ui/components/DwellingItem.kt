package com.imperatorofdwelling.android.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil3.compose.AsyncImage
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.entities.Dwelling
import com.imperatorofdwelling.android.presentation.ui.theme.h4_grey
import com.imperatorofdwelling.android.presentation.ui.theme.h4_white
import com.imperatorofdwelling.android.presentation.ui.theme.largeDp
import kotlinx.coroutines.launch


@Composable
fun DwellingItem(
    dwelling: Dwelling,
    onLikeClick: suspend (String, Boolean) -> Boolean,
    modifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
            val (mainImage, likeImage, nameText, addressText, priceText, markText) = createRefs()
            AsyncImage(
                model = dwelling.imageUrl,
                contentScale = ContentScale.FillWidth,
                contentDescription = null,
                modifier = imageModifier
                    .border(width = 3.dp, color = Color.Transparent)
                    .width(360.dp)
                    .clip(RoundedCornerShape(largeDp))
                    .constrainAs(mainImage) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    })
            val isLiked = remember { mutableStateOf(dwelling.isLiked) }
            val coroutineScope = rememberCoroutineScope()
            Image(
                painter = if (isLiked.value) painterResource(R.drawable.like_filled)
                else painterResource(R.drawable.like_default),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 19.dp, end = 18.dp)
                    .fillMaxWidth(0.1f)
                    .fillMaxHeight(0.166f)
                    .clickable {
                        coroutineScope.launch {
                            onLikeClick(dwelling.id, !isLiked.value)
                        }
                    }
                    .constrainAs(likeImage) {
                        top.linkTo(mainImage.top)
                        end.linkTo(mainImage.end)
                    }
            )
            Text(
                text = dwelling.name,
                style = h4_white,
                modifier = Modifier
                    .padding(top = 12.dp, start = 4.dp)
                    .constrainAs(nameText) {
                        top.linkTo(mainImage.bottom)
                        start.linkTo(mainImage.start)
                    })

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(start = 4.dp, top = 2.dp)
                    .constrainAs(addressText) {
                        top.linkTo(nameText.bottom)
                        start.linkTo(nameText.start)
                    }) {
                Image(
                    painter = painterResource(R.drawable.point),
                    contentDescription = null
                )
                Text(
                    text = dwelling.address,
                    style = h4_grey
                )
            }


            Text(
                text = dwelling.price.toString(),
                style = h4_white,
                modifier = Modifier
                    .padding(top = 12.dp, end = 4.dp)
                    .constrainAs(priceText) {
                        top.linkTo(mainImage.bottom)
                        end.linkTo(mainImage.end)
                    })

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(top = 8.dp, end = 4.dp)
                    .constrainAs(markText) {
                        top.linkTo(nameText.bottom)
                        end.linkTo(priceText.end)
                    }) {
                Image(
                    painter = painterResource(R.drawable.star),
                    contentDescription = null
                )
                Text(
                    text = dwelling.mark.toString(),
                    style = h4_grey
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(top = 4.dp, start = 4.dp, end = 4.dp)
                .fillMaxWidth()
        ) {
            val painterType = when (dwelling.type) {
                "hotel" -> painterResource(id = R.drawable.hotel)
                "apartment" -> painterResource(R.drawable.apart)
                "house" -> painterResource(id = R.drawable.home)
                else -> {
                    painterResource(id = R.drawable.apart)
                }
            }
            val textType = when (dwelling.type) {
                "hotel" -> stringResource(id = R.string.hotel)
                "apartment" -> stringResource(R.string.aparts)
                "house" -> stringResource(id = R.string.house)
                else -> {
                    stringResource(id = R.string.aparts)
                }
            }
            Advantage(
                painter = painterType,
                text = textType
            )
            Advantage(
                painter = painterResource(R.drawable.beds_amenity),
                text = buildString {
                    append("${dwelling.numberOfBeds}")
                    append(stringResource(R.string.beds))
                }
            )
            Advantage(
                painter = painterResource(R.drawable.rooms_amenity),
                text = buildString {
                    append("${dwelling.room} ")
                    append(stringResource(id = R.string.rooms))
                }
            )

        }
    }
}