package com.imperatorofdwelling.android.presentation.ui.user_profile

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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.ui.edit_profile.EditProfileScreen
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
import org.koin.androidx.compose.koinViewModel

class UserProfile : Screen {
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<UserProfileViewModel>()
        val state = viewModel.state.collectAsState()
        Scaffold(
            topBar = {
                UserProfileTopBar(userRole = "tenant")
            }
        ) { paddingValues ->
            UserProfileBody(
                modifier = Modifier.padding(paddingValues),
                phone = state.value.user?.phone ?: "",
                name = state.value.user?.name ?: "",
                email = state.value.user?.email ?: ""
            )
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

    @Composable
    fun UserProfileBody(
        modifier: Modifier,
        email: String,
        name: String,
        phone: String
    ) {
        val navigator = LocalNavigator.currentOrThrow
        Column(modifier = modifier.scrollable(rememberScrollState(), Orientation.Vertical)) {
            Column {
                Row(
                    modifier = Modifier
                        .padding(largeDp)
                        .fillMaxWidth()
                ) {
                    Box {
                        Image(
                            painter = painterResource(id = R.drawable.big_profile),
                            contentDescription = null
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
//                                Image(
//                                    painter = painterResource(id = R.drawable.mail),
//                                    contentDescription = null
//                                )
                                Spacer(modifier = Modifier.width(2.dp))
                                Text(text = email, style = h4_grey)
                            }
                            Spacer(modifier = Modifier.height(4.dp))

                            Row(verticalAlignment = Alignment.CenterVertically) {
//                                Image(
//                                    painter = painterResource(id = R.drawable.phone),
//                                    contentDescription = null
//                                )
                                Spacer(modifier = Modifier.width(2.dp))
                                if(phone == ""){
                                    Text(text = stringResource(R.string.enter_phone_number), style = h4_grey)
                                }
                                else{
                                    Text(text = phone, style = h4_grey)
                                }
                            }
                        }
                        Image(
                            painter = painterResource(id = R.drawable.edit),
                            contentDescription = stringResource(R.string.edit_profile),
                            modifier = Modifier.clickable{
                                navigator.push(EditProfileScreen())
                            }
                        )
                    }

                }
                HorizontalDivider(thickness = 0.5.dp, color = GreyDividerColor)
                PlateButton(
                    painter = painterResource(id = R.drawable.setting),
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
                    Text(text = stringResource(R.string.our_mission_and_values), style = h4_accent)
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
                    Row {
                        Text(text = stringResource(R.string.log_out), style = forButtons16dp)
                    }
                }

            }
        }
    }
}