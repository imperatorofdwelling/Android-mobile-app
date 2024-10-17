package com.imperatorofdwelling.android.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.entities.Euro
import com.imperatorofdwelling.android.presentation.entities.Period
import com.imperatorofdwelling.android.presentation.entities.Price
import com.imperatorofdwelling.android.presentation.ui.theme.h4_grey
import com.imperatorofdwelling.android.presentation.ui.theme.h4_white

@Preview
@Composable
fun PreviewDwellingItem() {
    DwellingItem(
        painter = painterResource(R.drawable.example_hotel_image),
        liked = false,
        price = Price(Euro(), 40, Period.Daily)
    )
}

@Composable
fun DwellingItem(
    painter: Painter,
    liked: Boolean,
    price: Price,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        ConstraintLayout (modifier = Modifier.fillMaxWidth()){
            val (mainImage, likeImage, nameText, addressText, priceText, markText) = createRefs()
            Image(contentScale = ContentScale.Crop,
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .border(width = 3.dp, color = Color.Transparent)
                    .fillMaxWidth()
                    .constrainAs(mainImage) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    })

            Image(
                painter = if (liked) painterResource(R.drawable.like_filled)
                else painterResource(R.drawable.like_default),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 19.dp, end = 18.dp)
                    .fillMaxWidth(0.1f)
                    .fillMaxHeight(0.166f)
                    .constrainAs(likeImage) {
                        top.linkTo(mainImage.top)
                        end.linkTo(mainImage.end)
                    }
            )

            Text(
                text = stringResource(R.string.example_name_hotel),
                style = h4_white,
                modifier = Modifier
                    .padding(top = 4.dp, start = 4.dp)
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
                    text = stringResource(R.string.example_address),
                    style = h4_grey
                )
            }


            Text(
                text = price.toString(),
                style = h4_white,
                modifier = Modifier
                    .padding(top = 8.dp, end = 4.dp)
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
                    text = stringResource(R.string.example_mark),
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
            Advantage(
                painter = painterResource(R.drawable.apart),
                text = stringResource(R.string.aparts)
            )
            Advantage(
                painter = painterResource(R.drawable.apart),
                text = stringResource(R.string.aparts)
            )
            Advantage(
                painter = painterResource(R.drawable.apart),
                text = stringResource(R.string.aparts)
            )

        }
    }
}