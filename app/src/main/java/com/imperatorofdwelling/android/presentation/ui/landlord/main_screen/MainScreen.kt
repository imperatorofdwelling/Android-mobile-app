package com.imperatorofdwelling.android.presentation.ui.landlord.main_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import coil3.compose.AsyncImage
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.ui.landlord.main_screen.components.Calendar
import com.imperatorofdwelling.android.presentation.ui.theme.White
import com.imperatorofdwelling.android.presentation.ui.theme.h3
import com.imperatorofdwelling.android.presentation.ui.theme.h5
import com.imperatorofdwelling.android.presentation.ui.theme.largeDp

class MainScreen : Screen {
    @Composable
    override fun Content() {
        Scaffold(
            topBar = { MainScreenTopBar() }
        ) { paddingValues ->
            MainScreenBody(modifier = Modifier.padding(paddingValues))
        }
    }

    @Composable
    private fun MainScreenTopBar(
        modifier: Modifier = Modifier
    ) {
        Column(
            modifier = modifier.padding(horizontal = largeDp)
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

    @Composable
    private fun MainScreenBody(
        avatarUrl: String = "",
        userName: String = "",
        modifier: Modifier = Modifier
    ) {
        var showAvatarDialog by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .padding(start = largeDp, end = largeDp, top = 8.dp)
                .then(modifier)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                if (avatarUrl.isNotEmpty()) {
                    AsyncImage(
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable {
                                showAvatarDialog = !showAvatarDialog
                            }
                            .size(100.dp),
                        contentScale = ContentScale.Crop,
                        model = avatarUrl,
                        contentDescription = null
                    )
                } else {
                    Image(
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable {
                                showAvatarDialog = !showAvatarDialog
                            },
                        painter = painterResource(id = R.drawable.big_profile),
                        contentDescription = null,
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(stringResource(R.string.hello), style = h5)
                    Text(userName, style = h3)
                }
            }

        }
    }
}