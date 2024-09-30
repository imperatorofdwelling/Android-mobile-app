package com.imperatorofdwelling.android.presentation.ui.sign_In

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.ui.components.ExtraLargeSpacer
import com.imperatorofdwelling.android.presentation.ui.components.MediumSpacer
import com.imperatorofdwelling.android.presentation.ui.components.PrimaryButton
import com.imperatorofdwelling.android.presentation.ui.components.PrimaryTextField
import com.imperatorofdwelling.android.presentation.ui.theme.MyApplicationTheme
import com.imperatorofdwelling.android.presentation.ui.theme.extraLargeDp

@Composable
fun SignInScreen() {
    SignInScreenBody(
        email = "",
        onEmailChange = {},
        password = "",
        onPasswordChange = {},
        onGoogleLoginClick = {},
        onTwitterLoginClick = {},
        onSignInClick = {}
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
    onSignInClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(extraLargeDp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier
                .size(64.dp)
                .align(Alignment.Start),
            imageVector = ImageVector.vectorResource(id = R.drawable.logo),
            tint = Color.White,
            contentDescription = null
        )

        ExtraLargeSpacer()

        Text(
            text = stringResource(id = R.string.sign_in),
            style = MaterialTheme.typography.titleLarge,
        )

        ExtraLargeSpacer()

        MediumSpacer()
        PrimaryTextField(
            value = email,
            onValueChange = onEmailChange,
            hint = stringResource(id = R.string.email)
        )
        MediumSpacer()
        PrimaryTextField(
            value = password,
            onValueChange = onPasswordChange,
            hint = stringResource(id = R.string.password)
        )

        ExtraLargeSpacer()

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = stringResource(id = R.string.forgot_your_password),
                style = TextStyle(
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
            text = stringResource(id = R.string.sign_up),
            enabled = false, //todo: field validation
            onClick = onSignInClick
        )

        ExtraLargeSpacer()

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        )
        {
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
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                buildAnnotatedString {
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
                }
            )
        }
    }
}

@Composable
@Preview
fun SignInScreenPreview() {
    MyApplicationTheme {
        SignInScreenBody(
            email = "",
            onEmailChange = {},
            password = "",
            onPasswordChange = {},
            onGoogleLoginClick = {},
            onTwitterLoginClick = {},
            onSignInClick = {}
        )
    }
}