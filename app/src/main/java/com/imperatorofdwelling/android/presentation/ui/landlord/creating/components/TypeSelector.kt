package com.imperatorofdwelling.android.presentation.ui.landlord.creating.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.imperatorofdwelling.android.presentation.entities.dwelling.Apartment
import com.imperatorofdwelling.android.presentation.entities.dwelling.Hotel
import com.imperatorofdwelling.android.presentation.entities.dwelling.House
import com.imperatorofdwelling.android.presentation.ui.home_screen.components.Plate
import com.imperatorofdwelling.android.presentation.ui.theme.White
import com.imperatorofdwelling.android.presentation.ui.theme.h4_white
import com.imperatorofdwelling.android.presentation.ui.theme.smallDp

@Composable
fun TypeSelector(
    modifier: Modifier = Modifier,
    houseSelected: Boolean,
    houseClick: (Boolean) -> Unit,
    apartmentSelected: Boolean,
    apartmentClick: (Boolean) -> Unit,
    hotelSelected: Boolean,
    hotelClick: (Boolean) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Plate(isActive = houseSelected, onClick = houseClick, modifier = Modifier.size(100.dp)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    painter = painterResource(id = House.iconDrawableId),
                    contentDescription = stringResource(id = House.nameStringId),
                    tint = White
                )
                Spacer(modifier = Modifier.height(smallDp))
                Text(style = h4_white, text = stringResource(id = House.nameStringId))
            }
        }
        Plate(isActive = apartmentSelected, onClick = apartmentClick, modifier = Modifier.size(100.dp)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    painter = painterResource(id = Apartment.iconDrawableId),
                    contentDescription = stringResource(id = Apartment.nameStringId),
                    tint = White
                )
                Spacer(modifier = Modifier.height(smallDp))
                Text(
                    style = h4_white,
                    text = stringResource(id = Apartment.nameStringId)
                )
            }
        }
        Plate(isActive = hotelSelected, onClick = hotelClick, modifier = Modifier.size(100.dp)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    painter = painterResource(id = Hotel.iconDrawableId),
                    contentDescription = stringResource(id = Hotel.nameStringId),
                    tint = White
                )
                Spacer(modifier = Modifier.height(smallDp))
                Text(style = h4_white, text = stringResource(id = Hotel.nameStringId))
            }
        }
    }
}