package com.imperatorofdwelling.android.presentation.ui.landlord.main_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.ui.landlord.main_screen.components.Calendar
import com.imperatorofdwelling.android.presentation.ui.theme.White
import com.imperatorofdwelling.android.presentation.ui.theme.largeDp

class MainScreen : Screen {
    @Composable
    override fun Content() {
        Scaffold { paddingValues ->
            MainScreenBody(modifier = Modifier.padding(paddingValues))
        }
    }

    @Composable
    fun MainScreenBody(
        modifier: Modifier = Modifier
    ) {
        Column(
            modifier = modifier.padding(largeDp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Calendar()
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.notification_bell),
                        contentDescription = stringResource(R.string.notification),
                        tint = White
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.add_stay),
                        contentDescription = stringResource(
                            R.string.add_stay
                        ),
                        tint = White
                    )
                }
            }
        }
    }
}