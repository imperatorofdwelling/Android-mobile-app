package com.imperatorofdwelling.android.presentation.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.imperatorofdwelling.android.R


val sfProFontFamily = FontFamily(
    Font(R.font.sf_pro, FontWeight.Normal)
)

val h5 = TextStyle(
    color = Grey1,
    fontFamily = sfProFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.5.sp
)

val h2 = TextStyle(
    color = White,
    fontFamily = sfProFontFamily,
    fontWeight = FontWeight(500),
    fontSize = 18.sp,
    lineHeight = 21.sp,

)

val forButtons = TextStyle(
    color = White,
    fontFamily = sfProFontFamily,
    fontWeight = FontWeight(500),
    fontSize = 16.sp,
    lineHeight = 19.sp,
)

val title = TextStyle(
    color = White,
    fontFamily = sfProFontFamily,
    fontWeight = FontWeight(500),
    fontSize = 20.sp,
    lineHeight = 23.sp
)

val h4_accent = TextStyle(
    color = Accent,
    fontFamily = sfProFontFamily,
    fontWeight = FontWeight(400),
    fontSize = 14.sp,
    lineHeight = 16.sp,
    textAlign = TextAlign.Right
)

val body = TextStyle(
    color = White,
    fontFamily = sfProFontFamily,
    fontWeight = FontWeight(300),
    fontSize = 12.sp,
    lineHeight = 16.sp
)

val h4_grey = TextStyle(
    color = Grey1,
    fontFamily = sfProFontFamily,
    fontWeight = FontWeight(400),
    fontSize = 14.sp,
    lineHeight = 16.sp
)

val h4_white = TextStyle(
    color = White,
    fontFamily = sfProFontFamily,
    fontWeight = FontWeight(400),
    fontSize = 14.sp,
    lineHeight = 16.sp
)

val h3 = TextStyle(
    color = White,
    fontFamily = sfProFontFamily,
    fontWeight = FontWeight(500),
    fontSize = 16.sp,
    lineHeight = 19.sp
)