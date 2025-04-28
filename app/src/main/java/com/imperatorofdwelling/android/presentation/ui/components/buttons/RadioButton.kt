package com.imperatorofdwelling.android.presentation.ui.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.imperatorofdwelling.android.presentation.ui.theme.Accent
import com.imperatorofdwelling.android.presentation.ui.theme.Accent2
import com.imperatorofdwelling.android.presentation.ui.theme.Grey1

@Composable
fun RadioButton(
    modifier: Modifier = Modifier,
    isActive: Boolean = false,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(24.dp)
            .clip(CircleShape)
            .border(width = 1.dp, color = Grey1, shape = CircleShape)
    ) {
        if (isActive) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(24.dp)
                    .background(Accent2),
            ) {
                Box(
                    Modifier
                        .clip(CircleShape)
                        .size(10.dp)
                        .background(Accent)
                )
            }
        }
    }
}
