package com.imperatorofdwelling.android.presentation.ui.sign_In

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.ui.components.ExtraLargeSpacer
import com.imperatorofdwelling.android.presentation.ui.components.LargeSpacer
import com.imperatorofdwelling.android.presentation.ui.components.PrimaryButton
import com.imperatorofdwelling.android.presentation.ui.components.SmallSpacer
import com.imperatorofdwelling.android.presentation.ui.components.text_fields.PrimaryTextField
import com.imperatorofdwelling.android.presentation.ui.navigation.MainNavigation
import com.imperatorofdwelling.android.presentation.ui.sign_up.SignUpScreen
import com.imperatorofdwelling.android.presentation.ui.theme.extraLargeDp
import com.imperatorofdwelling.android.presentation.ui.theme.h4_accent
import com.imperatorofdwelling.android.presentation.ui.theme.h4_error
import org.koin.androidx.compose.koinViewModel

class SignInScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = koinViewModel<SignInViewModel>()
        val state = viewModel.state.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        SignInScreenBody(
            email = state.value.email,
            onEmailChange = viewModel::onEmailChange,
            password = state.value.password,
            onPasswordChange = viewModel::onPasswordChange,
            onGoogleLoginClick = viewModel::onGoogleLoginClick,
            onTwitterLoginClick = viewModel::onTwitterLoginClick,
            onSignInClick = {
                viewModel.onSignInClick(
                    callBackOnCompletion = { navigator.push(MainNavigation()) }
                )
            },
            onSignUpClick = {
                navigator.push(SignUpScreen())
            },
            hasPasswordError = state.value.passwordError,
            hasEmailError = state.value.emailError,
            serverHasError = state.value.serverHasError,
            signInEnable = !viewModel.hasAnyError() && !viewModel.isEmptyFieldExist(),
            onSkipClick = {
                navigator.push(MainNavigation())
            }
        )
    }

    @Composable
    fun SignInScreenBody(
        email: String,
        onEmailChange: (String) -> Unit,
        password: String,
        onPasswordChange: (String) -> Unit,
        onGoogleLoginClick: () -> Unit,
        onTwitterLoginClick: () -> Unit,
        onSignInClick: () -> Unit,
        onSignUpClick: () -> Unit,
        onSkipClick: () -> Unit,
        hasEmailError: Boolean = false,
        hasPasswordError: Boolean = false,
        serverHasError: Boolean = false,
        signInEnable: Boolean = false
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(extraLargeDp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    modifier = Modifier.size(64.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.logo),
                    tint = Color.White,
                    contentDescription = null
                )
                Text(text = "Skip", style = h4_accent, modifier = Modifier.clickable {
                    onSkipClick()
                })
            }

            Spacer(modifier = Modifier.height(90.dp))

            Text(
                text = stringResource(id = R.string.sign_in),
                style = MaterialTheme.typography.titleLarge,
            )


            if (serverHasError) {
                Text(
                    modifier = Modifier.padding(vertical = 7.dp),
                    text = stringResource(id = R.string.incorrect_email_or_password),
                    style = h4_error
                )
            } else {
                Spacer(modifier = Modifier.height(28.dp))
            }


            val emailErrorString = stringResource(id = R.string.email_is_incorrect)
            PrimaryTextField(
                modifier = Modifier.height(48.dp),
                value = email,
                onValueChange = onEmailChange,
                hint = stringResource(id = R.string.email),
                hasError = hasEmailError,
                errorString = emailErrorString
            )

            LargeSpacer()
            val passwordErrorString = stringResource(id = R.string.password_error)
            PrimaryTextField(
                modifier = Modifier.height(48.dp),
                value = password,
                onValueChange = onPasswordChange,
                hint = stringResource(id = R.string.password),
                hasError = hasPasswordError,
                visualTransformation = PasswordVisualTransformation(),
                errorString = passwordErrorString
            )

            SmallSpacer()

            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = stringResource(id = R.string.forgot_your_password), style = TextStyle(
                        fontFamily = MaterialTheme.typography.labelMedium.fontFamily,
                        fontSize = MaterialTheme.typography.labelMedium.fontSize,
                        fontWeight = MaterialTheme.typography.labelMedium.fontWeight,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                )
            }

            ExtraLargeSpacer()

            PrimaryButton(
                modifier = Modifier.height(56.dp),
                text = stringResource(id = R.string.sign_in),
                enabled = signInEnable,
                onClick = onSignInClick
            )

            ExtraLargeSpacer()

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onGoogleLoginClick) {
                    Icon(
                        modifier = Modifier.size(40.dp),
                        imageVector = ImageVector.vectorResource(id = R.drawable.logo_google),
                        tint = Color.Unspecified,
                        contentDescription = null
                    )
                }

                ExtraLargeSpacer()

                Text(
                    text = stringResource(id = R.string.or),
                    style = MaterialTheme.typography.labelSmall
                )

                ExtraLargeSpacer()

                IconButton(onClick = onTwitterLoginClick) {
                    Icon(
                        modifier = Modifier.size(40.dp),
                        imageVector = ImageVector.vectorResource(id = R.drawable.logo_twitter),
                        tint = Color.Unspecified,
                        contentDescription = null
                    )
                }
            }

            ExtraLargeSpacer()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onSignUpClick()
                    },
                horizontalArrangement = Arrangement.Center
            ) {
                Text(buildAnnotatedString {
                    withStyle(style = MaterialTheme.typography.labelMedium.toSpanStyle()) {
                        append(stringResource(id = R.string.don_t_have_an_account) + " ")
                    }
                    withStyle(
                        SpanStyle(
                            fontFamily = MaterialTheme.typography.labelMedium.fontFamily,
                            fontSize = MaterialTheme.typography.labelMedium.fontSize,
                            fontWeight = FontWeight(600),
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    ) {
                        append(stringResource(id = R.string.sign_up))
                    }
                })
            }
        }
    }
}