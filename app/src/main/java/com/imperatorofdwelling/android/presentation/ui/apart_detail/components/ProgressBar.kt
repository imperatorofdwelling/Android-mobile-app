package com.imperatorofdwelling.android.presentation.ui.apart_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imperatorofdwelling.android.presentation.ui.theme.Accent
import com.imperatorofdwelling.android.presentation.ui.theme.SecondGreyStroke

@Composable
@Preview
fun ProgressBarPreview() {
    ProgressBar(0.5f)
}

@Composable
fun ProgressBar(progress: Float) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(2.dp))
            .fillMaxWidth()
            .height(8.dp)
            .background(color = SecondGreyStroke, shape = RoundedCornerShape(2.dp))
    ) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(2.dp))
                .fillMaxWidth(progress)
                .fillMaxHeight()
                .background(color = Accent, shape = RoundedCornerShape(2.dp))
        ) {}
    }
}