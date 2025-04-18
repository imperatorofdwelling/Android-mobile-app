package com.imperatorofdwelling.android.presentation.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.ui.theme.GreyDividerColor
import com.imperatorofdwelling.android.presentation.ui.theme.White
import com.imperatorofdwelling.android.presentation.ui.theme.h5

@Composable
fun Attention(
    modifier: Modifier = Modifier,
    message: String
) {
    Row(
        modifier = modifier
            .border(width = 1.dp, color = GreyDividerColor, shape = RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.padding(16.dp),
            painter = painterResource(id = R.drawable.attention),
            contentDescription = stringResource(
                R.string.attention
            ),
            tint = White,
        )
        Text(modifier = Modifier.padding(end = 16.dp), text = message, style = h5)
    }
}

@Composable
@Preview
fun PreviewAttention() {
    Attention(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        message = stringResource(R.string.upload_a_minimum_of_3_photos_of_your_facility)
    )
}
