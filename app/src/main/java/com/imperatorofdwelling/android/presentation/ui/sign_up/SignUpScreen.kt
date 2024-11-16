package com.imperatorofdwelling.android.presentation.ui.sign_up

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
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
import com.imperatorofdwelling.android.presentation.ui.components.MediumSpacer
import com.imperatorofdwelling.android.presentation.ui.components.PrimaryButton
import com.imperatorofdwelling.android.presentation.ui.components.PrimaryTextField
import com.imperatorofdwelling.android.presentation.ui.navigation.MainNavigation
import com.imperatorofdwelling.android.presentation.ui.theme.extraLargeDp
import com.imperatorofdwelling.android.presentation.ui.theme.extraSmallDp
import com.imperatorofdwelling.android.presentation.ui.theme.h4_accent
import com.imperatorofdwelling.android.presentation.ui.theme.h4_error
import org.koin.androidx.compose.koinViewModel

class SignUpScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<SignUpViewModel>()
        val state = viewModel.state.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        SignUpScreenBody(
            name = state.value.name,
            onNameChange = viewModel::onNameChange,
            email = state.value.email,
            onEmailChange = viewModel::onEmailChange,
            password = state.value.password,
            onPasswordChange = viewModel::onPasswordChange,
            confirmPassword = state.value.confirmPassword,
            onConfirmPasswordChange = viewModel::onConfirmPasswordChange,
            agreedToTerms = state.value.agreedToTerms,
            enableSignUp = state.value.agreedToTerms && (!viewModel.hasAnyError()) && (!viewModel.isEmptyFieldExist()),
            onAgreedToTermsChange = viewModel::onAgreedToTermsChange,
            onGoogleLoginClick = viewModel::onGoogleLoginClick,
            onTwitterLoginClick = viewModel::onTwitterLoginClick,
            onSignUpClick = {
                viewModel.onSignUpClick(callBackOnCompletion = { navigator.push(MainNavigation()) })
            },
            onSignInClick = {
                navigator.pop()
            },
            onSkipClick = {
                navigator.push(MainNavigation())
            },
            lengthNameError = state.value.nameError,
            emailError = state.value.emailError,
            passwordError = state.value.passwordError,
            confirmPasswordError = state.value.confirmPasswordError,
            serverTextError = state.value.serverTextError
        )
    }

    @Composable
    fun SignUpScreenBody(
        name: String,
        onNameChange: (String) -> Unit,
        email: String,
        onEmailChange: (String) -> Unit,
        password: String,
        onPasswordChange: (String) -> Unit,
        confirmPassword: String,
        onConfirmPasswordChange: (String) -> Unit,
        enableSignUp: Boolean,
        agreedToTerms: Boolean,
        onAgreedToTermsChange: (Boolean) -> Unit,
        onGoogleLoginClick: () -> Unit,
        onTwitterLoginClick: () -> Unit,
        onSignUpClick: () -> Unit,
        onSignInClick: () -> Unit,
        onSkipClick: () -> Unit,
        lengthNameError: Boolean = false,
        emailError: Boolean = false,
        passwordError: Boolean = false,
        confirmPasswordError: Boolean = false,
        serverTextError: String? = null
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(extraLargeDp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
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

            ExtraLargeSpacer()

            Text(
                text = stringResource(id = R.string.sign_up),
                style = MaterialTheme.typography.titleLarge,
            )

            if (serverTextError != null) {
                MediumSpacer()
                Text(text = serverTextError, style = h4_error)
                MediumSpacer()
            } else {
                ExtraLargeSpacer()
            }

            PrimaryTextField(
                value = name,
                onValueChange = onNameChange,
                hint = stringResource(id = R.string.name),
                hasError = lengthNameError
            )
            MediumSpacer()
            PrimaryTextField(
                value = email,
                onValueChange = onEmailChange,
                hint = stringResource(id = R.string.email),
                hasError = emailError
            )
            MediumSpacer()
            PrimaryTextField(
                value = password,
                onValueChange = onPasswordChange,
                hint = stringResource(id = R.string.password),
                hasError = passwordError,
                visualTransformation = PasswordVisualTransformation()
            )
            MediumSpacer()
            PrimaryTextField(
                value = confirmPassword,
                onValueChange = onConfirmPasswordChange,
                hint = stringResource(id = R.string.confirm_password),
                hasError = confirmPasswordError,
                visualTransformation = PasswordVisualTransformation()
            )

            ExtraLargeSpacer()

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Checkbox(
                    modifier = Modifier
                        .size(24.dp)
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(extraSmallDp)
                        )
                        .clip(RoundedCornerShape(extraSmallDp)),
                    checked = agreedToTerms,
                    onCheckedChange = onAgreedToTermsChange,
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color.Transparent,
                        uncheckedColor = Color.Transparent,
                        checkmarkColor = Color.White
                    )
                )
                Text(buildAnnotatedString {
                    withStyle(style = MaterialTheme.typography.labelMedium.toSpanStyle()) {
                        append(stringResource(id = R.string.i_reed_and_agree_to))
                    }
                    withStyle(
                        SpanStyle(
                            fontFamily = MaterialTheme.typography.labelMedium.fontFamily,
                            fontSize = MaterialTheme.typography.labelMedium.fontSize,
                            fontWeight = FontWeight(600),
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    ) {
                        append(stringResource(id = R.string.terms_and_conditions))
                    }
                })
            }

            ExtraLargeSpacer()

            PrimaryButton(
                modifier = Modifier.height(56.dp),
                text = stringResource(id = R.string.sign_up),
                enabled = enableSignUp,
                onClick = onSignUpClick
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
                    .clickable { onSignInClick() },
                horizontalArrangement = Arrangement.Center
            ) {
                Text(buildAnnotatedString {
                    withStyle(style = MaterialTheme.typography.labelMedium.toSpanStyle()) {
                        append(stringResource(id = R.string.already_have_an_account))
                    }
                    withStyle(
                        SpanStyle(
                            fontFamily = MaterialTheme.typography.labelMedium.fontFamily,
                            fontSize = MaterialTheme.typography.labelMedium.fontSize,
                            fontWeight = FontWeight(600),
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    ) {
                        append(stringResource(id = R.string.signin))
                    }
                })
            }
        }
    }
}