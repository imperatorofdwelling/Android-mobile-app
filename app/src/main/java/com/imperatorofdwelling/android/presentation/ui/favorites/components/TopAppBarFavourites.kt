package com.imperatorofdwelling.android.presentation.ui.favorites.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.ui.theme.DarkGrey
import com.imperatorofdwelling.android.presentation.ui.theme.extraLargeDp
import com.imperatorofdwelling.android.presentation.ui.theme.h1
import com.imperatorofdwelling.android.presentation.ui.theme.largeDp

@Composable
fun TopAppBarFavourites(text: String) {
    val backgroundNotificationBell = painterResource(R.drawable.notification_bell)
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = DarkGrey)
            .padding(vertical = largeDp, horizontal = extraLargeDp)
    ) {
        Text(
            text = text, style = h1
        )

        Image(
            painter = backgroundNotificationBell,
            contentDescription = null
        )
    }
}