package com.imperatorofdwelling.android.presentation.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.imperatorofdwelling.android.R

@Composable
fun Mark(mark: Double, color: Color, style: TextStyle) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(top = 8.dp, end = 4.dp)
    ) {
        Icon(
            painter = painterResource(R.drawable.star),
            contentDescription = null,
            tint = color
        )
        Text(
            text = mark.toString(),
            style = style
        )
    }
}