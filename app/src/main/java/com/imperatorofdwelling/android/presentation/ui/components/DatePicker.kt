package com.imperatorofdwelling.android.presentation.ui.components


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.entities.DateEntity
import com.imperatorofdwelling.android.presentation.ui.theme.Accent
import com.imperatorofdwelling.android.presentation.ui.theme.AccentCalendar
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
    onSelectDate: () -> String
) {

    val calendar = Calendar.getInstance()

    val currentDay = calendar.get(Calendar.DAY_OF_MONTH)
    val isCurrentYear = calendar.get(Calendar.YEAR) == selectedYear
    val isCurrentMonth = calendar.get(Calendar.MONTH) + 1 == selectedMonth
    val prevMonth = if (selectedMonth == 1) 12 else selectedMonth - 1
    calendar.set(selectedYear, prevMonth - 1, 1)
    val daysOfPrevMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

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

    Column(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(smallDp)
            )
            .clip(RoundedCornerShape(smallDp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ExtraLargeSpacer()
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AnimatedVisibility(visible = !(isCurrentMonth && isCurrentYear)) {
                IconButton(onClick = {
                    if (!(isCurrentMonth && isCurrentYear)) {
                        onPreviousMonthClick()
                    }
                }) {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        imageVector = ImageVector.vectorResource(id = R.drawable.arrow_prev),
                        tint = Color.White,
                        contentDescription = null
                    )
                }
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
        val daysList = getDaysCurrentMonth(daysOfMonth, selectedMonth, selectedYear)

        val daysPrevMonthList = if (selectedMonth - 1 == 0) {
            getDaysPrevMonth(daysOfPrevMonth, firstDayOfMonth, 12, selectedYear - 1)
        } else {
            getDaysPrevMonth(daysOfPrevMonth, firstDayOfMonth, selectedMonth - 1, selectedYear)
        }
        val daysNextMonth = getDaysNextMonth(
            count = 42 - (daysPrevMonthList.size + daysOfMonth),
            month = selectedMonth + 1,
            year = selectedYear
        )

        var firstDay by remember {
            mutableStateOf(DateEntity(-1, month = -1, year = -1))
        }

        var secondDay by remember {
            mutableStateOf(DateEntity(-1, month = -1, year = -1))
        }

        val onDayClick = { day: DateEntity ->
            if (firstDay.day == -1) {
                firstDay = day
            } else if (secondDay.day == -1 && day != firstDay) {
                secondDay = day
                if (secondDay < firstDay) {
                    secondDay = firstDay.also { firstDay = secondDay }
                }
            } else {
                firstDay = day
                secondDay = secondDay.copy(day = -1, month = -1, year = -1)
            }
        }

        val sizeDay = 32.dp
        val boxModifier = Modifier.size(sizeDay).wrapContentWidth()
        LazyVerticalGrid(
            columns = GridCells.Fixed(7),
            modifier = Modifier.padding(16.dp).width(300.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(dayOfWeek) { item ->
                Box(contentAlignment = Alignment.Center, modifier = boxModifier) {
                    Text(text = item, style = h4_white)
                }
            }
            items(daysPrevMonthList) { item ->
                Box(contentAlignment = Alignment.Center, modifier = boxModifier
                    .clickable {
                        onPreviousMonthClick()
                        onDayClick(item)
                    }) {
                    if (item > firstDay && item < secondDay) {
                        WrapDay()
                    }
                    if (item == firstDay || item == secondDay) {
                        WrapDaySelected(item = item, firstDay = firstDay, secondDay = secondDay)
                        Text(
                            text = item.toString(),
                            style = h4_white,
                            modifier = Modifier.zIndex(2f)
                        )
                    } else {
                        Text(
                            text = item.toString(),
                            style = h4_grey,
                            modifier = Modifier.zIndex(2f)
                        )
                    }
                }
            }
            items(daysList) { item ->
                Box(contentAlignment = Alignment.Center, modifier = boxModifier) {
                    if (item > firstDay && item < secondDay) {
                        WrapDay()
                    }
                    if (item == firstDay || item == secondDay) {
                        WrapDaySelected(item = item, firstDay = firstDay, secondDay = secondDay)
                    }
                    if (isCurrentMonth && isCurrentYear && item.day < currentDay) {
                        Text(
                            text = item.toString(), style = h4_grey, modifier = Modifier.zIndex(2f)
                        )
                    } else {
                        Text(text = item.toString(),
                            style = h4_white,
                            modifier = Modifier
                                .clickable {
                                    onDayClick(item)
                                }
                                .zIndex(2f))
                    }
                }
            }
            items(daysNextMonth) { item ->
                Box(contentAlignment = Alignment.Center, modifier = boxModifier
                    .clickable {
                        onNextMonthClick()
                        onDayClick(item)
                    }) {
                    if (item > firstDay && item < secondDay) {
                        WrapDay()
                    }
                    if (item == firstDay || item == secondDay) {
                        WrapDaySelected(item = item, firstDay = firstDay, secondDay = secondDay)
                        Text(
                            text = item.toString(),
                            style = h4_white,
                            modifier = Modifier.zIndex(2f)
                        )
                    } else {
                        Text(
                            text = item.toString(),
                            style = h4_grey,
                            modifier = Modifier.zIndex(2f)
                        )
                    }
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
                selectedMonth = 1,
                selectedMonthName = "January",
                selectedYear = 2025,
                onPreviousMonthClick = { /*TODO*/ },
                onNextMonthClick = { /*TODO*/ },
                onSelectDate = { "" })
        }
    }
}

fun getDaysPrevMonth(
    daysOfPrevMonth: Int, firstDayOfWeek: Int,
    month: Int,
    year: Int
): List<DateEntity> {
    val firstDay = daysOfPrevMonth - firstDayOfWeek + 1
    val days = mutableListOf<DateEntity>()
    for (i in (firstDay)..daysOfPrevMonth) {
        days.add(DateEntity(day = i, month = month, year = year))
    }
    return days
}

fun getDaysCurrentMonth(
    dayOfCurrentMonth: Int,
    month: Int,
    year: Int
): List<DateEntity> {
    val days = mutableListOf<DateEntity>()
    for (i in 1..dayOfCurrentMonth) {
        days.add(DateEntity(day = i, month = month, year = year))
    }
    return days
}

fun getDaysNextMonth(count: Int, month: Int, year: Int): List<DateEntity> {
    val days = (1..count).toList()
    val res = mutableListOf<DateEntity>()
    for (i in days) {
        res.add(DateEntity(day = i, month = month, year = year))
    }
    return res
}


@Composable
private fun WrapDay() {
    Canvas(
        modifier = Modifier
            .size(32.dp)
            .zIndex(0f)
    ) {
        drawRect(
            color = AccentCalendar,
            topLeft = Offset(x = -8.dp.toPx(), y = 0.dp.toPx()),
            size = size.copy(width = 48.dp.toPx(), height = 32.dp.toPx()),
        )
    }
}

@Composable
private fun WrapDaySelected(
    item: DateEntity,
    firstDay: DateEntity,
    secondDay: DateEntity
) {
    Canvas(
        modifier = Modifier
            .size(24.dp)
            .zIndex(1f)
    ) {
        if (item == firstDay && secondDay.day != -1) {
            drawRect(
                color = AccentCalendar,
                topLeft = Offset(x = 16.dp.toPx(), y = -4.dp.toPx()),
                size = size.copy(width = 20.dp.toPx(), height = 32.dp.toPx())
            )
        } else if (item == secondDay && firstDay.day != -1) {
            drawRect(
                color = AccentCalendar,
                topLeft = Offset(x = -11.dp.toPx(), y = -4.dp.toPx()),
                size = size.copy(width = 20.dp.toPx(), height = 32.dp.toPx())
            )
        }
        drawCircle(color = Accent, radius = 16.dp.toPx())
    }
}