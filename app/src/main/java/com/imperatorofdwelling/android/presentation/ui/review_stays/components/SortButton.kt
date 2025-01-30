package com.imperatorofdwelling.android.presentation.ui.review_stays.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.ui.theme.DarkGrey
import com.imperatorofdwelling.android.presentation.ui.theme.GreyStroke
import com.imperatorofdwelling.android.presentation.ui.theme.White
import com.imperatorofdwelling.android.presentation.ui.theme.XXLdp
import com.imperatorofdwelling.android.presentation.ui.theme.h4_white

@Composable
fun SortButton(
    text: String,

    modifier: Modifier = Modifier,

    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .width(100.dp)
            .height(XXLdp)
            .clickable {
                onClick()
            }
            .background(
                DarkGrey,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 1.dp,
                color = GreyStroke,
                shape = RoundedCornerShape(8.dp)
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row{
            Spacer(modifier = Modifier.width(12.dp))
            Icon(
                painter = painterResource(id = R.drawable.sort),
                contentDescription = null,
                tint = White
            )
        }
        Row{
            Text(text = text, style = h4_white)
            Spacer(modifier = Modifier.width(12.dp))
        }
    }
}