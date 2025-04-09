package com.imperatorofdwelling.android.presentation.ui.home_screen.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.imperatorofdwelling.android.presentation.ui.theme.Accent
import com.imperatorofdwelling.android.presentation.ui.theme.DarkGrey
import com.imperatorofdwelling.android.presentation.ui.theme.DarkGreyStroke

@Composable
fun Plate(
    modifier: Modifier = Modifier,
    isActive: Boolean = false,
    onClick: (Boolean) -> Unit = { },
    content: @Composable () -> Unit
) {

    val targetColor = if (isActive) {
        Accent
    } else {
        DarkGrey
    }
    val color by animateColorAsState(
        targetValue = targetColor,
        animationSpec = tween(durationMillis = 300),
        label = ""
    )

    Box(
        modifier = modifier
            .clickable{
                onClick(!isActive)
            }
            .clip(shape = RoundedCornerShape(8.dp))
            .background(color = color, shape = RoundedCornerShape(8.dp))
            .border(width = 1.dp, color = DarkGreyStroke, shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center,
    ) {
        content()
    }
}