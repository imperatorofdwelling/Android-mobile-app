package com.imperatorofdwelling.android.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.entities.DateEntity
import com.imperatorofdwelling.android.presentation.ui.components.buttons.PrimaryButton
import com.imperatorofdwelling.android.presentation.ui.theme.extraLargeDp
import com.imperatorofdwelling.android.presentation.ui.theme.h2
import com.imperatorofdwelling.android.presentation.ui.theme.h3
import com.imperatorofdwelling.android.presentation.ui.theme.largeDp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetDatePicker(
    onDismissRequest: () -> Unit,
    selectedMonth: Int,
    selectedMonthName: String,
    selectedYear: Int,
    flexibility: Boolean,
    onPreviousMonthClick: () -> Unit,
    onNextMonthClick: () -> Unit,
    onFlexibilityClick: (Boolean) -> Unit,
    sheetState: SheetState,
    onDateSelected: (DateEntity, DateEntity?) -> Unit,
    modifier: Modifier = Modifier
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        containerColor = Color.Black,
        sheetState = sheetState
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = largeDp)
        ) {
            Spacer(modifier = Modifier.height(extraLargeDp))
            Text(text = stringResource(R.string.you_are_booking_for), style = h2)
            Spacer(modifier = Modifier.height(extraLargeDp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DatePicker(
                    selectedYear = selectedYear,
                    selectedMonth = selectedMonth,
                    selectedMonthName = selectedMonthName,
                    onPreviousMonthClick = onPreviousMonthClick,
                    onNextMonthClick = onNextMonthClick,
                    onSelectDate = onDateSelected
                )
            }
            Spacer(modifier = Modifier.height(extraLargeDp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = stringResource(R.string.i_m_flexible), style = h3)
                Spacer(modifier = Modifier.width(12.dp))
                Checkbox(checked = flexibility, onCheckedChange = onFlexibilityClick)
            }
            Spacer(modifier = Modifier.height(extraLargeDp))
            PrimaryButton(text = stringResource(R.string.apply)) {
                onDismissRequest()
            }

        }
    }
}
