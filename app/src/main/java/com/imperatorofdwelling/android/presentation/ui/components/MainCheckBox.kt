package com.imperatorofdwelling.android.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.imperatorofdwelling.android.R

@Composable
fun MainCheckBox(
    checked: Boolean,
    modifier: Modifier = Modifier,
    onCheckedChange: ((Boolean) -> Unit)? = null
) {

    Box(
        modifier = modifier
            .clip(CircleShape)
            .size(32.dp).clickable {
                onCheckedChange?.invoke(!checked)
            },
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = if (checked) {
                painterResource(id = R.drawable.checkbox_active)
            } else {
                painterResource(id = R.drawable.checkbox_dissable)
            },
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
    }
//    Checkbox(
//        modifier = modifier
//            .size(24.dp)
//            .clip(RoundedCornerShape(extraSmallDp)),
//        checked = checked,
//        onCheckedChange = onCheckedChange,
//        colors = CheckboxDefaults.colors(
//            checkedColor = Accent,
//            uncheckedColor = BrightGray,
//            checkmarkColor = White
//        )
//    )
}
