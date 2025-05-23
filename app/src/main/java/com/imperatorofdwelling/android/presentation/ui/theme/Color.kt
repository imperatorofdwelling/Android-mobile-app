package com.imperatorofdwelling.android.presentation.ui.theme

import androidx.compose.ui.graphics.Color

val DarkGrey = Color(0xFF131313)
val Grey1 = Color(0xFF757575)
val White = Color(0xFFFFFFFF)
val Black = Color(0xFF000000)
val Accent = Color(0xFF006BE6)
val Accent2 = Color(0xFF0251B8)
val AccentCalendar = Color(0xFF183658)
val GreyDividerColor = Color(0xFF222225)
val Transparent = Color(0x00000000)
val Red = Color(0xFFFF0000)
val GreyStroke = Color(0xFF1B1B1C)
val SecondGreyStroke = Color(0xFF1C1C1E)
val DarkGreyStroke = Color(0xFF1F1F1F)

fun Color.setAlpha(alpha: Float): Color {
    return this.copy(alpha = alpha)
}