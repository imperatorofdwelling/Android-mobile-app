package com.imperatorofdwelling.android.presentation.ui.error

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.ui.components.buttons.PrimaryButton
import com.imperatorofdwelling.android.presentation.ui.theme.XXLdp
import com.imperatorofdwelling.android.presentation.ui.theme.extraLargeDp
import com.imperatorofdwelling.android.presentation.ui.theme.h1
import com.imperatorofdwelling.android.presentation.ui.theme.h3_grey
import com.imperatorofdwelling.android.presentation.ui.theme.largeDp

class ErrorScreen(
    private val throwable: Throwable,
    private val onRetry: () -> Unit
) : Screen {
    @Composable
    override fun Content() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = largeDp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Column(
                modifier = Modifier.padding(vertical = 60.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.network_error),
                    contentDescription = stringResource(R.string.network_error)
                )
                Spacer(modifier = Modifier.height(XXLdp))
                Text(text = stringResource(R.string.there_is_no_internet), style = h1)
                Spacer(modifier = Modifier.height(largeDp))
                Text(
                    text = stringResource(R.string.turn_on_the_internet_to_continue),
                    style = h3_grey
                )
                Text(
                    text = stringResource(R.string.using_the_app),
                    style = h3_grey
                )
            }
            Spacer(modifier = Modifier.height(extraLargeDp))
            PrimaryButton(
                text = stringResource(R.string.try_again),
                onClick = onRetry,
                modifier = Modifier.width(286.dp)
            )
        }
    }
}