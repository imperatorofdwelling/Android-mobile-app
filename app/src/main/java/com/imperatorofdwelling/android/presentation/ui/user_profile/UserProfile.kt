package com.imperatorofdwelling.android.presentation.ui.user_profile

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import coil3.compose.AsyncImage
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.ui.components.LargeSpacer
import com.imperatorofdwelling.android.presentation.ui.components.RegistrationDialog
import com.imperatorofdwelling.android.presentation.ui.components.buttons.PrimaryButton
import com.imperatorofdwelling.android.presentation.ui.components.buttons.StrokeButton
import com.imperatorofdwelling.android.presentation.ui.edit_profile.EditProfileScreen
import com.imperatorofdwelling.android.presentation.ui.home_screen.HomeTab
import com.imperatorofdwelling.android.presentation.ui.navigation.NavigationModel
import com.imperatorofdwelling.android.presentation.ui.sign_up.SignUpScreen
import com.imperatorofdwelling.android.presentation.ui.theme.Black
import com.imperatorofdwelling.android.presentation.ui.theme.DarkGrey
import com.imperatorofdwelling.android.presentation.ui.theme.GreyDividerColor
import com.imperatorofdwelling.android.presentation.ui.theme.forButtons16dp
import com.imperatorofdwelling.android.presentation.ui.theme.h2
import com.imperatorofdwelling.android.presentation.ui.theme.h3
import com.imperatorofdwelling.android.presentation.ui.theme.h4_accent
import com.imperatorofdwelling.android.presentation.ui.theme.h4_grey
import com.imperatorofdwelling.android.presentation.ui.theme.h5
import com.imperatorofdwelling.android.presentation.ui.theme.largeDp
import com.imperatorofdwelling.android.presentation.ui.user_profile.component.PlateButton
import com.imperatorofdwelling.android.presentation.ui.utils.ApplicationManager
import com.imperatorofdwelling.android.presentation.ui.utils.LCE
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

class UserProfile : Screen {
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<UserProfileViewModel>()
        val state = viewModel.state.collectAsState()
        val lce = viewModel.lce.collectAsState()
        val navigator = LocalNavigator.currentOrThrow
        val tabNavigator = LocalTabNavigator.current
        val navigationManager = koinInject<NavigationModel>()
        LaunchedEffect(key1 = Unit) {
            viewModel.updateState()
        }
        Scaffold(
            topBar = {
                UserProfileTopBar(userRole = "tenant")
            }
        ) { paddingValues ->
            if (lce.value == LCE.Loading) {
                UserProfilePlaceholder(
                    modifier = Modifier.padding(paddingValues)
                )
            } else if (state.value.isRegistered) {
                UserProfileBody(
                    modifier = Modifier.padding(paddingValues),
                    phone = state.value.user?.phone ?: "",
                    name = state.value.user?.name ?: "",
                    email = state.value.user?.email ?: "",
                    avatarUrl = state.value.userAvatarUrl ?: "",
                    onLogOutClick = viewModel::onLogOutClicked,
                    onAvatarSelected = viewModel::onAvatarSelected
                )
            } else {
                Box(modifier = Modifier.padding(paddingValues)) {
                    RegistrationDialog(onDismissRequest = { tabNavigator.current = HomeTab }) {
                        navigator.popAll()
                        navigationManager.onSetVisible(false)
                        navigator.push(
                            SignUpScreen(
                                isInitialScreen = false,
                                navigationModel = navigationManager,
                            )
                        )
                    }
                    UserProfilePlaceholder()
                }
            }
        }
    }

    @Composable
    fun UserProfileTopBar(
        userRole: String
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = DarkGrey),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = stringResource(id = R.string.profile), style = h2)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = userRole, style = h5)
            Spacer(modifier = Modifier.height(14.dp))

        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun UserProfileBody(
        modifier: Modifier,
        email: String,
        name: String,
        phone: String,
        avatarUrl: String,
        onLogOutClick: () -> Unit,
        onAvatarSelected: (ByteArray, String) -> Unit
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

        val navigator = LocalNavigator.currentOrThrow
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
        Column(modifier = modifier.scrollable(rememberScrollState(), Orientation.Vertical)) {
            Column {
                Row(
                    modifier = Modifier
                        .padding(largeDp)
                        .fillMaxWidth()
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
                    Spacer(modifier = Modifier.width(largeDp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            if (name != "") {
                                Text(style = h3, text = name)
                            } else {
                                Text(style = h3, text = stringResource(R.string.enter_name))

                            }
                            Spacer(modifier = Modifier.height(12.dp))

                            Row(verticalAlignment = Alignment.CenterVertically) {

                                Spacer(modifier = Modifier.width(2.dp))
                                Text(text = email, style = h4_grey)
                            }
                            Spacer(modifier = Modifier.height(4.dp))

                            Row(verticalAlignment = Alignment.CenterVertically) {

                                Spacer(modifier = Modifier.width(2.dp))
                                if (phone == "") {
                                    Text(
                                        text = stringResource(R.string.enter_phone_number),
                                        style = h4_grey
                                    )
                                } else {
                                    Text(text = phone, style = h4_grey)
                                }
                            }
                        }
                        Image(
                            painter = painterResource(id = R.drawable.edit),
                            contentDescription = stringResource(R.string.edit_profile),
                            modifier = Modifier.clickable {
                                navigator.push(EditProfileScreen())
                            }
                        )
                    }
                }
                HorizontalDivider(thickness = 0.5.dp, color = GreyDividerColor)
                PlateButton(
                    painter = painterResource(id = R.drawable.setting_transparent),
                    text = stringResource(id = R.string.settings)
                ) {
                    Text(text = stringResource(R.string.notifications), style = h4_accent)
                    Spacer(modifier = Modifier.height(largeDp))
                    Text(text = stringResource(R.string.payment_method), style = h4_accent)
                    Spacer(modifier = Modifier.height(largeDp))
                }
                HorizontalDivider(thickness = 0.5.dp, color = GreyDividerColor)
                PlateButton(
                    painter = painterResource(id = R.drawable.help),
                    text = stringResource(R.string.help_and_support)
                ) {
                    Text(
                        text = stringResource(R.string.frequently_asked_questions_faq),
                        style = h4_accent
                    )
                    Spacer(modifier = Modifier.height(largeDp))
                    Text(text = stringResource(R.string.contact_details), style = h4_accent)
                    Spacer(modifier = Modifier.height(largeDp))
                    Text(text = stringResource(R.string.live_chat), style = h4_accent)
                    Spacer(modifier = Modifier.height(largeDp))
                    Text(text = stringResource(R.string.privacy_policy), style = h4_accent)
                    Spacer(modifier = Modifier.height(largeDp))
                    Text(text = stringResource(R.string.terms_of_use), style = h4_accent)
                    Spacer(modifier = Modifier.height(largeDp))
                }
                HorizontalDivider(thickness = 0.5.dp, color = GreyDividerColor)
                PlateButton(
                    painter = painterResource(id = R.drawable.about),
                    text = stringResource(R.string.about_us)
                ) {
                    Text(text = stringResource(R.string.company_information), style = h4_accent)
                    Spacer(modifier = Modifier.height(largeDp))
                    Text(
                        text = stringResource(R.string.our_mission_and_values),
                        style = h4_accent
                    )
                    Spacer(modifier = Modifier.height(largeDp))
                }
                HorizontalDivider(thickness = 0.5.dp, color = GreyDividerColor)
                Column(modifier = Modifier.padding(largeDp)) {
                    Row {
                        //Image()
                        Text(
                            text = stringResource(R.string.list_your_property),
                            style = forButtons16dp
                        )
                    }
                    Spacer(modifier = Modifier.height(largeDp))
                    val localContext = LocalContext.current
                    Row {
                        Text(
                            text = stringResource(R.string.log_out),
                            style = forButtons16dp,
                            modifier = Modifier.clickable {
                                onLogOutClick()
                                ApplicationManager.restartApp(localContext)
                            }
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun UserProfilePlaceholder(
        modifier: Modifier = Modifier
    ) {
        val shimmer = rememberShimmer(
            shimmerBounds = ShimmerBounds.View,
        )

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .shimmer(shimmer)
                        .background(
                            color = Color.Gray.copy(alpha = 0.3f),
                            shape = CircleShape
                        )
                )
                Spacer(modifier = Modifier.padding(largeDp))
                Column {
                    repeat(3) {
                        Box(
                            modifier = Modifier
                                .width(120.dp)
                                .height(15.dp)
                                .shimmer(shimmer)
                                .background(
                                    color = Color.Gray.copy(alpha = 0.3f),
                                    shape = RoundedCornerShape(4.dp)
                                )
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }

            }


            Spacer(modifier = Modifier.height(16.dp))

            repeat(3) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .shimmer(shimmer)
                        .background(
                            color = Color.Gray.copy(alpha = 0.3f),
                            shape = RoundedCornerShape(4.dp)
                        )
                )

                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}