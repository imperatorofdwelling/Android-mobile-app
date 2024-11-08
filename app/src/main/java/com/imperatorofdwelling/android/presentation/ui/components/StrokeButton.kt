package com.imperatorofdwelling.android.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.imperatorofdwelling.android.presentation.ui.theme.Accent
import com.imperatorofdwelling.android.presentation.ui.theme.Transparent
import com.imperatorofdwelling.android.presentation.ui.theme.forButtons
import com.imperatorofdwelling.android.presentation.ui.theme.smallDp

@Composable
fun StrokeButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth().height(50.dp)
            .background(
                color = Transparent,
                shape = RoundedCornerShape(smallDp)
            )
            .border(
                width = 1.dp,
                color = Accent,
                shape = RoundedCornerShape(smallDp)
            ),
        shape = RoundedCornerShape(smallDp),
        enabled = enabled,
        onClick = onClick,
        colors = ButtonColors(
            containerColor = Transparent,
            contentColor = Transparent,
            disabledContentColor = Transparent,
            disabledContainerColor = Transparent
        )

    ) {
        Text(text = text, style = forButtons)
    }
}