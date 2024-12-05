package com.imperatorofdwelling.android.presentation.ui.components.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.imperatorofdwelling.android.R

@Composable
fun ChatButton(
    onClick: () -> Unit,

    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.chat_button),
        contentDescription = null,
        modifier = modifier
            .size(48.dp)
            .clickable {
                onClick()
            }
    )
}