package com.imperatorofdwelling.android.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.imperatorofdwelling.android.R

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = lightColorScheme(
        primary = Color(0xFF006BE6),
        secondary = Color.White,
        tertiary = Color(0xFF1DA1F2),
        surface = Color(0xFF131313),
        background = Color(0xFF000000),
        error = Color(0xFFEB2121),
    )
    val typography = Typography(
        titleLarge = TextStyle(
            fontFamily = FontFamily(Font(R.font.sfprotext_regular)),
            fontSize = 48.sp,
            fontWeight = FontWeight(700),
            color = Color.White
        ),
        labelSmall = TextStyle(
            fontFamily = FontFamily(Font(R.font.sfprotext_regular)),
            fontSize = 12.sp,
            fontWeight = FontWeight(300),
            color = Color.White
        ),
        labelMedium = TextStyle(
            fontFamily = FontFamily(Font(R.font.sfprotext_regular)),
            fontSize = 14.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFF757575),
        ),
        labelLarge = TextStyle(
            fontFamily = FontFamily(Font(R.font.sfprotext_regular)),
            fontSize = 16.sp,
            fontWeight = FontWeight(600),
            color = Color.White
        )
    )
    val shapes = Shapes(
        small = RoundedCornerShape(extraSmallDp),
        medium = RoundedCornerShape(smallDp),
        large = RoundedCornerShape(largeDp)
    )

    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}