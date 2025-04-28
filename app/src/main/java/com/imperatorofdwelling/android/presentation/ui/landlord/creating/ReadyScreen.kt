package com.imperatorofdwelling.android.presentation.ui.landlord.creating

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.ui.components.buttons.PrimaryButton
import com.imperatorofdwelling.android.presentation.ui.landlord.main_screen.MainScreen
import com.imperatorofdwelling.android.presentation.ui.theme.h1
import com.imperatorofdwelling.android.presentation.ui.theme.h3

class ReadyScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.verify),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = stringResource(R.string.your_advert_is_ready), style = h1)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.go_to_the_my_objects_section_and_view_all_your_adverts),
                style = h3
            )
            PrimaryButton(text = stringResource(R.string.home), onClick = {
                navigator.popAll()
                navigator.push(
                    MainScreen()
                )
            })
        }
    }
}