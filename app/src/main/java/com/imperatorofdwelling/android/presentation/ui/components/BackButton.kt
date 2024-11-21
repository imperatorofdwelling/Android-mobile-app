package com.imperatorofdwelling.android.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.ui.theme.mediumDp

@Composable
fun BackButton(
    onClick: () -> Unit,

    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.back_button),
        contentDescription = null,
        modifier = modifier
            .size(48.dp)
            .padding(end = mediumDp)
            .clickable {
                onClick()
            }
    )
}