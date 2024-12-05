package com.imperatorofdwelling.android.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.imperatorofdwelling.android.presentation.entities.dwelling.TypeOfDwelling
import com.imperatorofdwelling.android.presentation.ui.theme.DarkGrey
import com.imperatorofdwelling.android.presentation.ui.theme.GreyStroke
import com.imperatorofdwelling.android.presentation.ui.theme.White
import com.imperatorofdwelling.android.presentation.ui.theme.h4_white

@Composable
fun TypePlate(
    type: TypeOfDwelling,

    modifier: Modifier = Modifier
) {
    val name = stringResource(id = type.nameStringId)
    Row(
        modifier = modifier
            .border(width = 1.dp, color = GreyStroke, shape = RoundedCornerShape(8.dp))
            .background(color = DarkGrey, shape = RoundedCornerShape(8.dp))
            .padding(12.dp)
    ) {
        Icon(
            painter = painterResource(id = type.iconDrawableId),
            contentDescription = name,
            tint = White,
        )
        Spacer(modifier.width(10.dp))
        Text(
            text = stringResource(id = type.nameStringId),
            style = h4_white
        )
    }
}