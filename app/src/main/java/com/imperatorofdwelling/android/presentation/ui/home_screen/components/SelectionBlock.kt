package com.imperatorofdwelling.android.presentation.ui.home_screen.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.ui.components.buttons.PrimaryButton
import com.imperatorofdwelling.android.presentation.ui.components.text_fields.IconTextField
import com.imperatorofdwelling.android.presentation.ui.theme.Accent
import com.imperatorofdwelling.android.presentation.ui.theme.Transparent
import com.imperatorofdwelling.android.presentation.ui.theme.smallDp

@Composable
fun SelectionBlock(
    onClickTypeSelection: () -> Unit,
    onClickResidentsSelection: () -> Unit,
    onClickDatePicker: () -> Unit,
    selectedTypesString: () -> String,
    selectedDatesString: () -> String,
    selectedResidentsString: () -> String,
    showSelectionResidents: Boolean,
    showSelectionDate: Boolean,
    showSelectionTypes: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, top = 16.dp)
    ) {

        val homeIconPainter = painterResource(R.drawable.home)

        IconTextField(
            unfocusedIcon = homeIconPainter,
            focusedIcon = homeIconPainter,
            placeholderText = stringResource(R.string.type_of_dwelling_you_need),
            value = selectedTypesString(),
            modifier = Modifier
                .border(
                    color = if (showSelectionTypes) Accent else Transparent,
                    width = 1.dp,
                    shape = RoundedCornerShape(smallDp)
                )
                .clickable {
                    onClickTypeSelection()
                },
            enabled = false
        )


        Spacer(modifier = Modifier.padding(top = 8.dp))

        val calendarIconPainter = painterResource(R.drawable.calendar)
        IconTextField(
            unfocusedIcon = calendarIconPainter,
            focusedIcon = calendarIconPainter,
            placeholderText = stringResource(R.string.dates),
            value = selectedDatesString(),
            modifier = Modifier
                .border(
                    color = if (showSelectionDate) Accent else Transparent,
                    width = 1.dp,
                    shape = RoundedCornerShape(smallDp)
                )
                .clickable {
                    onClickDatePicker()
                },
            enabled = false
        )
        Spacer(modifier = Modifier.padding(top = 8.dp))

        val residentsIconPainter = painterResource(R.drawable.resident)
        IconTextField(
            unfocusedIcon = residentsIconPainter,
            focusedIcon = residentsIconPainter,
            placeholderText = stringResource(R.string.residents),
            value = selectedResidentsString(),
            modifier = Modifier
                .border(
                    color = if (showSelectionResidents) Accent else Transparent,
                    width = 1.dp,
                    shape = RoundedCornerShape(smallDp)
                )
                .clickable {
                    onClickResidentsSelection()
                },
            enabled = false
        )
        PrimaryButton(
            text = stringResource(R.string.apply),
            modifier = Modifier.padding(top = 8.dp),
        ) {

        }
    }
}