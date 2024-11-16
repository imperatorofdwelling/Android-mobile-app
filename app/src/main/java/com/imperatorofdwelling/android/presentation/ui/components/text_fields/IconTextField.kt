package com.imperatorofdwelling.android.presentation.ui.components.text_fields

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.ui.theme.Accent
import com.imperatorofdwelling.android.presentation.ui.theme.Red
import com.imperatorofdwelling.android.presentation.ui.theme.Transparent
import com.imperatorofdwelling.android.presentation.ui.theme.h2
import com.imperatorofdwelling.android.presentation.ui.theme.smallDp

const val MAX_LINES_ICON_FIELD = 1

@Preview
@Composable
fun IconTextFieldPreview(
) {
    IconTextField(
        unfocusedIcon = painterResource(R.drawable.search_icon_unfocused),
        focusedIcon = painterResource(R.drawable.search_icon_focused),
        placeholderText = stringResource(R.string.enter_the_city_name),
    )
}

@Composable
fun IconTextField(
    modifier: Modifier = Modifier,
    unfocusedIcon: Painter,
    focusedIcon: Painter,
    placeholderText: String?,
    value: String = "",
    hasError: Boolean = false,
    onValueChanged: ((String) -> Unit)? = null
) {
    val focused = remember { mutableStateOf(false) }

    val icon = if (focused.value) focusedIcon else unfocusedIcon

    TextField(
        value = value,
        textStyle = h2,
        maxLines = MAX_LINES_ICON_FIELD,
        onValueChange = onValueChanged ?: {},
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(smallDp)
            )
            .clip(RoundedCornerShape(smallDp))
            .border(
                width = 1.dp,
                color = if (focused.value) {
                    Accent
                } else if (hasError) {
                    Red
                } else {
                    Transparent
                },
                shape = RoundedCornerShape(smallDp)
            )
            .onFocusChanged { newFocusValue ->
                focused.value = newFocusValue.isFocused
            }
            .then(modifier),
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
}



