package com.imperatorofdwelling.android.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.ui.theme.forButtons

enum class DefaultButtonState {
    DEFAULT, PRESSED, STROKE, DISABLED
}

@Composable
fun DefaultButton(
    text: String,
    state: DefaultButtonState,
    onCLick: () -> Unit,
    modifier: Modifier
) {

    val painter = when (state) {
        DefaultButtonState.DEFAULT -> painterResource(R.drawable.button_long_default)
        DefaultButtonState.PRESSED -> painterResource(R.drawable.button_long_pressed)
        DefaultButtonState.STROKE -> painterResource(R.drawable.button_long_stroke)
        DefaultButtonState.DISABLED -> painterResource(R.drawable.button_long_dissabled)
    }

    Box(contentAlignment = Alignment.Center, modifier = modifier) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            painter = painter,
            contentDescription = null
        )
        Button(
            onClick = onCLick,
            colors = ButtonDefaults.buttonColors(
                Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        )
        {
            Text(text = text, style = forButtons)
        }
    }
}