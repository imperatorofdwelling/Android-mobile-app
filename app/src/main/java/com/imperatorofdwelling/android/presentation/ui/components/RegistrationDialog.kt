package com.imperatorofdwelling.android.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.ui.components.buttons.PrimaryButton
import com.imperatorofdwelling.android.presentation.ui.components.buttons.StrokeButton
import com.imperatorofdwelling.android.presentation.ui.theme.DarkGrey
import com.imperatorofdwelling.android.presentation.ui.theme.White
import com.imperatorofdwelling.android.presentation.ui.theme.extraLargeDp
import com.imperatorofdwelling.android.presentation.ui.theme.h4_grey
import com.imperatorofdwelling.android.presentation.ui.theme.h4_white
import com.imperatorofdwelling.android.presentation.ui.theme.largeDp
import com.imperatorofdwelling.android.presentation.ui.theme.setAlpha

@Composable
fun RegistrationDialog(
    onDismissRequest: () -> Unit,
    onGoToRegistrationClick: () -> Unit
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Box(
            contentAlignment = Alignment.TopEnd,
            modifier = Modifier.background(
                color = DarkGrey.setAlpha(0.9f),
                shape = RoundedCornerShape(14.dp)
            )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.cross),
                contentDescription = null,
                tint = White,
                modifier = Modifier.padding(24.dp).size(largeDp).clickable { onDismissRequest() }
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 50.dp, vertical = extraLargeDp)
            ) {

                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = stringResource(R.string.to_access_application_features),
                    style = h4_white
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.please_login_to_start_using_our_property_rental_app),
                    style = h4_grey
                )
                Spacer(modifier = Modifier.height(extraLargeDp))
                PrimaryButton(
                    text = stringResource(R.string.go_to_registration)
                ) {
                    onGoToRegistrationClick()
                }
                Spacer(modifier = Modifier.height(8.dp))
                StrokeButton(text = stringResource(R.string.no_sign_up)) {
                    onDismissRequest()
                }
            }
        }
    }
}