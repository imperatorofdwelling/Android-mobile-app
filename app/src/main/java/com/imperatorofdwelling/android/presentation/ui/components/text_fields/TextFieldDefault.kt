package com.imperatorofdwelling.android.presentation.ui.components.text_fields

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.zIndex
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.ui.theme.h2

const val MAX_LINES_DEFAULT_FIELD = 1

@Deprecated("Use PrimaryTextField and IconTextField")
@Composable
fun TextFieldDefault(
    modifier: Modifier = Modifier,
    placeholderText: String?,
    value: String? = "",
    onValueChanged: ((String) -> Unit)? = null,
    contentScale: ContentScale
) {
    val focused = remember { mutableStateOf(false) }

    val painterBackgroundFocused = painterResource(R.drawable.input_active)
    val painterBackgroundUnFocused = painterResource(R.drawable.input_hold)
    val painterBackground =
        if (focused.value) painterBackgroundFocused else painterBackgroundUnFocused

    Box(modifier = modifier) {
        androidx.compose.material3.TextField(
            value = value ?: "",
            textStyle = h2,
            maxLines = MAX_LINES_DEFAULT_FIELD,
            onValueChange = onValueChanged ?: {},
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { newFocusValue ->
                    focused.value = newFocusValue.isFocused
                },
            placeholder = { Text(text = placeholderText ?: "") },
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