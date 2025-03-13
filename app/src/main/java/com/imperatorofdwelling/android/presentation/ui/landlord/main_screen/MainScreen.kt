package com.imperatorofdwelling.android.presentation.ui.landlord.main_screen

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil3.compose.AsyncImage
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.ui.components.LargeSpacer
import com.imperatorofdwelling.android.presentation.ui.components.buttons.PrimaryButton
import com.imperatorofdwelling.android.presentation.ui.components.buttons.StrokeButton
import com.imperatorofdwelling.android.presentation.ui.landlord.creating.CreatingScreen
import com.imperatorofdwelling.android.presentation.ui.landlord.main_screen.components.Calendar
import com.imperatorofdwelling.android.presentation.ui.theme.Black
import com.imperatorofdwelling.android.presentation.ui.theme.White
import com.imperatorofdwelling.android.presentation.ui.theme.h3
import com.imperatorofdwelling.android.presentation.ui.theme.h5
import com.imperatorofdwelling.android.presentation.ui.theme.largeDp
import org.koin.androidx.compose.koinViewModel

class MainScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<MainViewModel>()
        val state = viewModel.state.collectAsState()
        Scaffold(
            topBar = { MainScreenTopBar() }
        ) { paddingValues ->
            MainScreenBody(
                modifier = Modifier.padding(paddingValues),
                onAvatarSelected = viewModel::onAvatarSelected,
                avatarUrl = state.value.userAvatarUrl,
                userName = state.value.userData?.name ?: "",
            )
        }
    }

    @Composable
    private fun MainScreenTopBar(
        modifier: Modifier = Modifier
    ) {
        val navigator = LocalNavigator.currentOrThrow
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
                        modifier = Modifier.clickable{
                            navigator.push(CreatingScreen())
                        },
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

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun MainScreenBody(
        modifier: Modifier = Modifier,
        onAvatarSelected: (ByteArray, String) -> Unit,
        avatarUrl: String = "",
        userName: String = ""
    ) {
        val context = LocalContext.current
        val launcher =
            rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri ->
                uri?.let {
                    val type = context.contentResolver.getType(it)
                    context.contentResolver.openInputStream(it).use { inputStream ->
                        val bytes = inputStream?.readBytes()
                        bytes?.let {
                            onAvatarSelected(bytes, type ?: "")
                            Log.d("type: ", "type $type")
                        }
                    }

                }
            }

        var showAvatarDialog by remember { mutableStateOf(false) }
        if (showAvatarDialog) {
            ModalBottomSheet(
                onDismissRequest = { showAvatarDialog = false },
                containerColor = Black
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = largeDp)
                ) {
                    LargeSpacer()
                    PrimaryButton(text = "Change your avatar") {
                        launcher.launch("image/*")
                        showAvatarDialog = false
                    }
                    LargeSpacer()
                    StrokeButton(text = "Cancel") {
                        showAvatarDialog = false
                    }
                    LargeSpacer()
                }
            }
        }
        Column(
            modifier = Modifier
                .padding(start = largeDp, end = largeDp, top = 24.dp)
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
                            .size(50.dp),
                        contentScale = ContentScale.Crop,
                        model = avatarUrl,
                        contentDescription = null
                    )
                } else {
                    Image(
                        modifier = Modifier
                            .size(50.dp)
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