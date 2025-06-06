package com.imperatorofdwelling.android.presentation.ui.components.buttons

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
fun LikeButton(
    isLiked: Boolean,

    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val painter = if(isLiked){
        painterResource(id = R.drawable.like_button_filled)
    } else {
        painterResource(id = R.drawable.like_button)
    }
    Image(
        painter = painter,
        contentDescription = null,
        modifier = modifier
            .size(48.dp)
            .padding(end = mediumDp)
            .clickable {
                onClick()
            }
    )
}