package com.imperatorofdwelling.android.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.ui.theme.h4_white

@Preview
@Composable
fun PreviewAdvantage(){
    Advantage(painter = painterResource(R.drawable.apart), text = stringResource(R.string.aparts))
}


@Composable
fun Advantage(
    painter: Painter,
    text: String,
    modifier: Modifier = Modifier
){
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically){
        Image(painter = painter, contentDescription = null)
        Text(text = text, style = h4_white, modifier = Modifier.padding(start = 6.dp))
    }
}