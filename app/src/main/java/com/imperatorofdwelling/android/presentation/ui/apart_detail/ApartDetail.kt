package com.imperatorofdwelling.android.presentation.ui.apart_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.entities.dwelling.Apartment
import com.imperatorofdwelling.android.presentation.ui.components.Mark
import com.imperatorofdwelling.android.presentation.ui.components.TypePlate
import com.imperatorofdwelling.android.presentation.ui.components.buttons.BackButton
import com.imperatorofdwelling.android.presentation.ui.components.buttons.LikeButton
import com.imperatorofdwelling.android.presentation.ui.components.buttons.ShareButton
import com.imperatorofdwelling.android.presentation.ui.theme.White
import com.imperatorofdwelling.android.presentation.ui.theme.extraLargeDp
import com.imperatorofdwelling.android.presentation.ui.theme.forButtons16dp
import com.imperatorofdwelling.android.presentation.ui.theme.h2
import com.imperatorofdwelling.android.presentation.ui.theme.h3
import com.imperatorofdwelling.android.presentation.ui.theme.h4_grey
import com.imperatorofdwelling.android.presentation.ui.theme.largeDp
import com.imperatorofdwelling.android.presentation.ui.theme.mediumDp

@Preview
@Composable
fun ApartDetailPreview() {
    ApartDetail().Content()
}


class ApartDetail : Screen {

    @Composable
    override fun Content() {
        ApartDetailBody()
    }

    @Composable
    fun ApartDetailBody() {
        Column {
            Box {
                Image(
                    painterResource(id = R.drawable.example_hotel_image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(270.dp)
                        .fillMaxWidth()
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = largeDp, end = largeDp, top = mediumDp)
                ) {
                    BackButton(onClick = { /*TODO*/ })
                    Row {
                        ShareButton(onClick = { /*TODO*/ })
                        LikeButton(onClick = { /*TODO*/ })
                    }
                }
            }
            Column(
                modifier = Modifier.padding(
                    start = largeDp,
                    end = largeDp,
                    top = extraLargeDp
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TypePlate(type = Apartment)
                    Mark(
                        4.5,
                        style = h3,
                        color = White
                    )
                }
                Text(
                    text = stringResource(R.string.example_name_hotel),
                    style = h2,
                    modifier = Modifier
                        .padding(top = 16.dp, start = 4.dp)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(start = 4.dp, top = 2.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.point),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = stringResource(R.string.example_address),
                        style = h4_grey
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = extraLargeDp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = stringResource(R.string.amenities), style = h2)
                    Text(text = stringResource(R.string.see_details), style = forButtons16dp)
                }
            }
        }
    }

}