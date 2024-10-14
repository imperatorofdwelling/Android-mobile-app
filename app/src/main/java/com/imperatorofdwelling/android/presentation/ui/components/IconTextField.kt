package com.imperatorofdwelling.android.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.imperatorofdwelling.android.R

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
    focusedIcon: Painter = unfocusedIcon,
    placeholderText: String?,
    value: String? = "",
    onValueChanged: ((String) -> Unit)? = null
) {

    var icon = remember { unfocusedIcon }
    val painterBackgroundFocused = painterResource(R.drawable.input_active)
    val painterBackgroundUnFocused = painterResource(R.drawable.input_hold)
    var painterBackground = painterBackgroundUnFocused
    Box (modifier = modifier.fillMaxWidth()){
        TextField(
            value = value ?: "",
            onValueChange = onValueChanged ?: {},
            modifier = Modifier.onFocusChanged {
                icon = if (it.isFocused) focusedIcon else unfocusedIcon
                painterBackground =
                    if (it.isFocused) {
                        painterBackgroundFocused
                    } else {
                        painterBackgroundUnFocused
                    }
            },
            placeholder = { Text(text = placeholderText ?: "") },
            leadingIcon = { Icon(painter = icon, contentDescription = null) }
        )
        Image(
            painter = painterBackground,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
    }

}