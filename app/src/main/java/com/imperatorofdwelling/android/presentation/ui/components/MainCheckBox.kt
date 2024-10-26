package com.imperatorofdwelling.android.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.imperatorofdwelling.android.presentation.ui.theme.extraSmallDp

@Composable
fun MainCheckBox(
    agreedToTerms: Boolean,
    onAgreedToTermsChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Checkbox(
        modifier = modifier
            .size(24.dp)
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(extraSmallDp)
            )
            .clip(RoundedCornerShape(extraSmallDp)),
        checked = agreedToTerms,
        onCheckedChange = onAgreedToTermsChange,
        colors = CheckboxDefaults.colors(
            checkedColor = Color.Transparent,
            uncheckedColor = Color.Transparent,
            checkmarkColor = Color.White
        )
    )
}
