package com.imperatorofdwelling.android.presentation.ui.components.buttons

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imperatorofdwelling.android.presentation.ui.theme.GreyStroke
import com.imperatorofdwelling.android.presentation.ui.theme.Transparent
import com.imperatorofdwelling.android.presentation.ui.theme.forButtons
import com.imperatorofdwelling.android.presentation.ui.theme.smallDp
import com.imperatorofdwelling.android.presentation.ui.theme.DarkGrey


@Preview
@Composable
fun GreyPreview() {
    GreyButton(text = "View all Reviews", onClick = {})
}

@Composable
fun GreyButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(
                color = DarkGrey,
                shape = RoundedCornerShape(smallDp)
            )
            .border(
                width = 1.dp,
                color = GreyStroke,
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