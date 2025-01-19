package com.imperatorofdwelling.android.presentation.ui.user_profile.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.ui.theme.extraLargeDp
import com.imperatorofdwelling.android.presentation.ui.theme.h3
import com.imperatorofdwelling.android.presentation.ui.theme.largeDp

@Composable
fun PlateButton(
    painter: Painter,
    text: String,
    modifier: Modifier = Modifier,

    expendContent: @Composable () -> Unit
) {
    Column(modifier = modifier.padding(horizontal = largeDp)){

        var isExpand by remember { mutableStateOf(false) }
        Row(modifier = Modifier.padding(vertical = extraLargeDp).fillMaxWidth().clickable{
            isExpand = !isExpand
        },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painter,
                contentDescription = stringResource(R.string.settings)
            )
            Spacer(modifier = Modifier.width(14.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val arrowPainter = if(isExpand){
                    painterResource(id = R.drawable.up_arrow)
                } else {
                    painterResource(id = R.drawable.down_arrow)
                }
                Text(text = text, style = h3)
                Image(painter = arrowPainter, contentDescription = null)
            }
        }
        AnimatedVisibility(visible = isExpand) {
            Column{
                expendContent()
            }
        }

    }
}