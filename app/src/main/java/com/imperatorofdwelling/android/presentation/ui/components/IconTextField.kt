package com.imperatorofdwelling.android.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.ui.theme.h2

private const val MAX_LINES_ICON_FIELD = 1

@Preview
@Composable
fun IconTextFieldPreview(
) {
    IconTextField(
        unfocusedIcon = painterResource(R.drawable.search_icon_unfocused),
        focusedIcon = painterResource(R.drawable.search_icon_focused),
        placeholderText = stringResource(R.string.enter_the_city_name),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun IconTextField(
    modifier: Modifier = Modifier,
    unfocusedIcon: Painter,
    focusedIcon: Painter,
    placeholderText: String?,
    value: String? = "",
    onValueChanged: ((String) -> Unit)? = null,
    contentScale: ContentScale
) {
    val focused = remember { mutableStateOf(false) }

    val icon = if (focused.value) focusedIcon else unfocusedIcon
    val painterBackgroundFocused = painterResource(R.drawable.input_active)
    val painterBackgroundUnFocused = painterResource(R.drawable.input_hold)
    val painterBackground =
        if (focused.value) painterBackgroundFocused else painterBackgroundUnFocused

    Box(modifier = modifier.fillMaxWidth()) {
        TextField(
            value = value ?: "",
            textStyle = h2,
            maxLines = MAX_LINES_ICON_FIELD,
            onValueChange = onValueChanged ?: {},
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { newFocusValue ->
                    focused.value = newFocusValue.isFocused
                },
            placeholder = { Text(text = placeholderText ?: "") },
            leadingIcon = {
                Icon(
                    painter = icon,
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = MaterialTheme.colorScheme.secondary
            )
        )
        Image(
            painter = painterBackground,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .zIndex(-1f),
            contentScale = contentScale
        )
    }
}
