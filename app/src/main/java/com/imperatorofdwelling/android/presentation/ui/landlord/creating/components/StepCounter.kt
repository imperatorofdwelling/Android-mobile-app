package com.imperatorofdwelling.android.presentation.ui.landlord.creating.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.imperatorofdwelling.android.presentation.ui.theme.Accent
import com.imperatorofdwelling.android.presentation.ui.theme.White

@Composable
fun StepCounter(
    modifier: Modifier = Modifier,
    step: Int = 1,
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {
        repeat(4) { index ->
            Box(
                modifier = Modifier
                    .background(color = if (index <= step - 1) Accent else White)
                    .fillMaxWidth(0.2f)
            )
        }
    }
}
