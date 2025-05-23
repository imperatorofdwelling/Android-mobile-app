package com.imperatorofdwelling.android.presentation.ui.components.text_fields

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.ui.theme.Accent
import com.imperatorofdwelling.android.presentation.ui.theme.Grey1
import com.imperatorofdwelling.android.presentation.ui.theme.Red
import com.imperatorofdwelling.android.presentation.ui.theme.Transparent
import com.imperatorofdwelling.android.presentation.ui.theme.White
import com.imperatorofdwelling.android.presentation.ui.theme.h2
import com.imperatorofdwelling.android.presentation.ui.theme.h4_grey
import com.imperatorofdwelling.android.presentation.ui.theme.h5
import com.imperatorofdwelling.android.presentation.ui.theme.largeDp
import com.imperatorofdwelling.android.presentation.ui.theme.mediumDp
import com.imperatorofdwelling.android.presentation.ui.theme.smallDp

@Composable
fun EditTextTrailing(
    modifier: Modifier = Modifier,
    trailingIcon: Painter,
    onClickTrailing: () -> Unit,
    placeholderText: String? = "",
    value: String = "",
    hasError: Boolean = false,
    onValueChanged: ((String) -> Unit)? = null,
    outFocus: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    errorString: String? = null,
    enabled: Boolean = true,
    icon: Painter? = null,
    maxLines: Int = 1
) {

    val focused = remember { mutableStateOf(outFocus) }

    val focusManager = LocalFocusManager.current

    LaunchedEffect(outFocus) {
        if (!outFocus) {
            focusManager.clearFocus()
        }
    }
    Box(modifier = modifier) {

        TextField(
            value = value,
            textStyle = h2,
            maxLines = maxLines,
            onValueChange = onValueChanged ?: {},
            keyboardOptions = keyboardOptions,
            modifier = Modifier
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
                .onFocusChanged { newFocus ->
                    focused.value = newFocus.isFocused
                }
                .then(modifier),
            placeholder = {
                Text(text = placeholderText ?: "", style = h4_grey)
            },
            enabled = enabled,
            trailingIcon = {
                AnimatedVisibility(visible = value.isNotEmpty()) {
                    Row(
                        modifier = Modifier
                            .padding(horizontal = largeDp)
                            .wrapContentSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = trailingIcon,
                            contentDescription = null,
                            tint = White,
                            modifier = Modifier.clickable {
                                onClickTrailing()
                            }
                        )
                        Spacer(modifier = Modifier.width(largeDp))
                    }
                }
            },
            leadingIcon = if (icon != null) {
                {
                    Icon(
                        painter = icon,
                        contentDescription = null,
                        tint = White
                    )
                }
            } else null,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = MaterialTheme.colorScheme.secondary,
                disabledContainerColor = Color.Transparent,
                disabledPlaceholderColor = Grey1
            )

        )

        AnimatedVisibility(
            visible = hasError && !focused.value,
            enter = slideInVertically(
                initialOffsetY = { -50 },
                animationSpec = tween(500)
            ) + fadeIn(initialAlpha = 0f, animationSpec = tween(500)),
            exit = slideOutVertically(
                targetOffsetY = { -50 },
                animationSpec = tween(500)
            ) + fadeOut(targetAlpha = 0f, animationSpec = tween(500))
        ) {
            Text(
                text = errorString ?: "",
                style = com.imperatorofdwelling.android.presentation.ui.theme.error,
                modifier = Modifier.padding(start = largeDp, top = mediumDp)
            )
        }
    }
}

@Composable
fun BigEditText(
    modifier: Modifier = Modifier,
    placeholderText: String,
    value: String,
    onValueChanged: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    enabled: Boolean,
    maxSize: Int,
    hint: String,
) {
    Column {
        EditTextTrailing(
            modifier = modifier,
            placeholderText = placeholderText,
            value = value,
            onValueChanged = { newValue ->
                if (newValue.length <= maxSize) onValueChanged(newValue)
            },
            keyboardOptions = keyboardOptions,
            enabled = enabled,
            maxLines = 1000,
            trailingIcon = painterResource(id = R.drawable.cross),
            onClickTrailing = {
                onValueChanged("")
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = hint, style = h5)
            Text(text = "${value.length}/$maxSize", style = h5)
        }
    }

}

//@Preview
//@Composable
//fun EditTextPreview() {
//    var value by remember { mutableStateOf("") }
//    BigEditText(
//        placeholderText = "Enter Text",
//        value = value,
//        onValueChanged = { value = it },
//        keyboardOptions = KeyboardOptions.Default,
//        enabled = true
//    )
//}