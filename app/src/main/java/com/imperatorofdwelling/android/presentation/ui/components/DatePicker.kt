package com.imperatorofdwelling.android.presentation.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.ui.theme.MyApplicationTheme
import com.imperatorofdwelling.android.presentation.ui.theme.smallDp

@Composable
fun DatePicker(
    selectedMonthName: String,
    selectedYear: String,
    onPreviousMonthClick: () -> Unit,
    onNextMonthClick: () -> Unit,

    ) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(smallDp)
            )
            .clip(RoundedCornerShape(smallDp))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onPreviousMonthClick) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.star),
                    tint = Color.White,
                    contentDescription = null
                )
            }

            ExtraLargeSpacer()

            Text(
                text = "$selectedMonthName $selectedYear",
                color = Color.White,
                style = MaterialTheme.typography.labelLarge
            )

            ExtraLargeSpacer()

            IconButton(onClick = onNextMonthClick) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.star),
                    tint = Color.White,
                    contentDescription = null
                )
            }
        }

    }
}

@Composable
@Preview
fun DatePickerPreview() {
    MyApplicationTheme {
        Surface() {
            DatePicker(
                selectedMonthName = "January",
                selectedYear = "2022",
                onPreviousMonthClick = { /*TODO*/ },
                onNextMonthClick = { /*TODO*/ }
            )
        }
    }
}