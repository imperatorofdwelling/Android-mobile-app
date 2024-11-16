package com.imperatorofdwelling.android.presentation.ui.components.text_fields

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.imperatorofdwelling.android.presentation.ui.theme.Accent
import com.imperatorofdwelling.android.presentation.ui.theme.Red
import com.imperatorofdwelling.android.presentation.ui.theme.Transparent
import com.imperatorofdwelling.android.presentation.ui.theme.smallDp

@Composable
fun PrimaryTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    hasError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    val focused = remember { mutableStateOf(false) }
    TextField(
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
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = hint, style = MaterialTheme.typography.labelMedium
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.secondary
        ),
        textStyle = TextStyle(
            fontFamily = MaterialTheme.typography.labelMedium.fontFamily,
            fontSize = MaterialTheme.typography.labelMedium.fontSize,
            fontWeight = MaterialTheme.typography.labelMedium.fontWeight,
            color = MaterialTheme.colorScheme.secondary
        ),
        singleLine = true,
        visualTransformation = visualTransformation
    )
}

