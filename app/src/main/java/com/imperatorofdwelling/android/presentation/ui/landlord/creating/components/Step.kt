package com.imperatorofdwelling.android.presentation.ui.landlord.creating.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.imperatorofdwelling.android.presentation.ui.theme.h3
import com.imperatorofdwelling.android.presentation.ui.theme.h4_grey

@Composable
fun Step(
    modifier: Modifier = Modifier,
    textTitle: String,
    textContent: String,
    painter: Painter,
    number: Int,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Text(text = number.toString(), style = h3)
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(text = textTitle, style = h3)
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = textContent, style = h4_grey)
            }
        }
        Image(painter, contentDescription = null)
    }
}