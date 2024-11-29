package com.imperatorofdwelling.android.presentation.ui.apart_detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.ui.components.SmallSpacer
import com.imperatorofdwelling.android.presentation.ui.components.buttons.ChatButton
import com.imperatorofdwelling.android.presentation.ui.theme.h2
import com.imperatorofdwelling.android.presentation.ui.theme.h4_grey


@Preview
@Composable
fun ProfilePreview() {
    ProfileCard({})
}

@Composable
fun ProfileCard(
    onClickChatButton: () -> Unit,

    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {

            Image(
                painter = painterResource(id = R.drawable.example_hotel_image),
                modifier = Modifier
                    .size(48.dp)
                    .clip(shape = RoundedCornerShape(8.dp)),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            SmallSpacer()

            Column {
                Text(text = stringResource(id = R.string.example_name_hotel), style = h2)
                Text(text = stringResource(R.string.hotel_chain), style = h4_grey)
            }
        }

        SmallSpacer()

        ChatButton(onClick = onClickChatButton)
    }
}