package com.imperatorofdwelling.android.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.imperatorofdwelling.android.presentation.ui.theme.MyApplicationTheme
import com.imperatorofdwelling.android.presentation.ui.theme.smallDp

@Composable
fun PrimaryTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String
) = TextField(
    modifier = Modifier
        .fillMaxWidth()
        .background(
            color = MaterialTheme.colorScheme.surface,
            shape = RoundedCornerShape(smallDp)
        )
        .clip(RoundedCornerShape(smallDp))
        .then(modifier),
    value = value, onValueChange = onValueChange,
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
    singleLine = true
)

@Composable
@Preview
fun PrimaryTextFieldPreview() {
    MyApplicationTheme {
        PrimaryTextField(
            value = "",
            onValueChange = {},
            hint = "Email"
        )
    }
}
