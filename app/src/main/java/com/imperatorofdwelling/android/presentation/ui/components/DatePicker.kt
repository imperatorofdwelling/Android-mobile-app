package com.imperatorofdwelling.android.presentation.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import com.imperatorofdwelling.android.presentation.ui.theme.h4_grey
import com.imperatorofdwelling.android.presentation.ui.theme.h4_white
import com.imperatorofdwelling.android.presentation.ui.theme.smallDp
import java.util.Calendar

@Composable
fun DatePicker(
    selectedMonth: Int,
    selectedMonthName: String,
    selectedYear: Int,
    onPreviousMonthClick: () -> Unit,
    onNextMonthClick: () -> Unit,
) {
    
    val calendar = Calendar.getInstance()
    calendar.set(selectedYear, selectedMonth - 1, 1)
    val firstDayOfMonth = when (calendar.get(Calendar.DAY_OF_WEEK)) {
        Calendar.MONDAY -> 0
        Calendar.TUESDAY -> 1
        Calendar.WEDNESDAY -> 2
        Calendar.THURSDAY -> 3
        Calendar.FRIDAY -> 4
        Calendar.SATURDAY -> 5
        Calendar.SUNDAY -> 6
        else -> 0
    }

    val daysOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

    val prevMonth = if (selectedMonth == 1) 12 else selectedMonth - 1
    calendar.set(selectedYear, prevMonth - 1, 1)
    val daysOfPrevMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(smallDp)
            )
            .clip(RoundedCornerShape(smallDp))
    ) {
        ExtraLargeSpacer()
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onPreviousMonthClick) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.arrow_prev),
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
                    imageVector = ImageVector.vectorResource(id = R.drawable.arrow_next),
                    tint = Color.White,
                    contentDescription = null
                )
            }
        }

        val dayOfWeek = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
        val daysList = getDaysCurrentMonth(daysOfMonth)
        val daysPrevMonthList = getDaysPrevMonth(daysOfPrevMonth, firstDayOfMonth)
        val daysNextMonth = getDaysNextMonth(7 - (daysPrevMonthList.size + daysOfMonth) % 7)
        LazyVerticalGrid(
            columns = GridCells.Fixed(7),
            modifier = Modifier.padding(24.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(dayOfWeek) { item ->
                Box(contentAlignment = Alignment.Center) {
                    Text(text = item, style = h4_white)
                }
            }
            items(daysPrevMonthList) { item ->
                Box(contentAlignment = Alignment.Center) {
                    Text(text = item.toString(), style = h4_grey)
                }
            }
            items(daysList) { item ->
                Box(contentAlignment = Alignment.Center) {
                    Text(text = item.toString(), style = h4_white)
                }
            }
            items(daysNextMonth) { item ->
                Box(contentAlignment = Alignment.Center) {
                    Text(text = item.toString(), style = h4_grey)
                }
            }
        }
        ExtraLargeSpacer()
    }
}

@Composable
@Preview
fun DatePickerPreview() {
    MyApplicationTheme {
        Surface() {
            DatePicker(
                selectedMonth = 10,
                selectedMonthName = "October",
                selectedYear = 2025,
                onPreviousMonthClick = { /*TODO*/ },
                onNextMonthClick = { /*TODO*/ }
            )
        }
    }
}

fun getDaysPrevMonth(
    daysOfPrevMonth: Int,
    firstDayOfWeek: Int
): List<Int> {
    val firstDay = daysOfPrevMonth - firstDayOfWeek + 1
    val days = mutableListOf<Int>()
    for (i in (firstDay)..daysOfPrevMonth) {
        days.add(i)
    }
    return days
}

fun getDaysCurrentMonth(
    dayOfCurrentMonth: Int,
): List<Int> {
    val days = mutableListOf<Int>()
    for (i in 1..dayOfCurrentMonth) {
        days.add(i)
    }
    return days
}

fun getDaysNextMonth(count: Int) = (1..count).toList()