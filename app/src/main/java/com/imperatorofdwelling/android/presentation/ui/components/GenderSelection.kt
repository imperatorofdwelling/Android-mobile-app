package com.imperatorofdwelling.android.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.ui.components.buttons.PrimaryButton
import com.imperatorofdwelling.android.presentation.ui.theme.GreyDividerColor
import com.imperatorofdwelling.android.presentation.ui.theme.extraLargeDp
import com.imperatorofdwelling.android.presentation.ui.theme.title

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenderSelection(
    onDismissRequest: () -> Unit,
    onCheckedMale: (Boolean) -> Unit,
    onCheckedFemale: (Boolean) -> Unit,
    isSelectedMale: Boolean,
    isSelectedFemale: Boolean
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        containerColor = Color.Black,
    ) {
        Column {
            Spacer(modifier = Modifier.height(extraLargeDp))

            Text(
                text = stringResource(id = R.string.gender),
                style = title,
                modifier = Modifier.padding(start = extraLargeDp)
            )

            Spacer(modifier = Modifier.height(extraLargeDp))

            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = GreyDividerColor
            )

            SelectItem(
                text = stringResource(R.string.female),
                painter = painterResource(id = R.drawable.female),
                onChecked = onCheckedFemale,
                isSelected = isSelectedFemale
            )

            SelectItem(
                text = stringResource(R.string.male),
                painter = painterResource(id = R.drawable.male),
                onChecked = onCheckedMale,
                isSelected = isSelectedMale
            )

            Spacer(modifier = Modifier.height(50.dp))

            PrimaryButton(
                text = stringResource(id = R.string.apply),
                modifier = Modifier.padding(start = extraLargeDp, end = extraLargeDp),
                onClick = {
                    onDismissRequest()
                }
            )
            Spacer(modifier = Modifier.height(extraLargeDp))
        }

    }
}