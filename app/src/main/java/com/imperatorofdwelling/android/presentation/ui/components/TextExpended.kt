package com.imperatorofdwelling.android.presentation.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.ui.theme.Accent
import com.imperatorofdwelling.android.presentation.ui.theme.White
import com.imperatorofdwelling.android.presentation.ui.theme.animation.animationSpecSlowly
import com.imperatorofdwelling.android.presentation.ui.theme.sfProFontFamily

@Preview
@Composable
fun TextExpendedPreview() {
    TextExpended(
        text = "Lorem ipsum dolor sit emet Lorem ipsum dolor sit emet" +
                " Lorem ipsum dolor sit emet Lorem ipsum dolor sit emet Lorem" +
                " ipsum dolor sit emet Lorem ipsum dolor sit emet Lorem ipsum dolor" +
                " sit emet Lorem ipsum dolor sit emet Lorem ipsum dolor sit emet" +
                " Lorem ipsum dolor sit emet Lorem ipsum dolor sit emet Lorem ipsum dolor sit "
    )
}


@Composable
fun TextExpended(
    text: String,
    collapsedLength: Int = 120,
    modifier: Modifier = Modifier,
) {
    var isExpended by remember { mutableStateOf(false) }
    val expendText = if (!isExpended) {
        stringResource(id = R.string.see_more)
    } else {
        stringResource(id = R.string.hide)
    }
    val annotatedString = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = White,
                fontFamily = sfProFontFamily,
                fontWeight = FontWeight(400),
                fontSize = 14.sp,
            )
        ) {
            append(
                if (isExpended) StringBuilder(text).append(' ') else StringBuilder(
                    text.substring(0, collapsedLength)
                )
                    .append("... ")
                    .toString()
            )
        }
        withStyle(
            style = SpanStyle(
                color = Accent,
                fontFamily = sfProFontFamily,
                fontWeight = FontWeight(400),
                fontSize = 14.sp,
            )
        ) {
            append(expendText)
        }
    }


    Text(
        text = annotatedString,
        modifier = modifier
            .clickable { isExpended = !isExpended }
            .animateContentSize(tween(500))
    )
}