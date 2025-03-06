package com.imperatorofdwelling.android.presentation.ui.landlord.main_screen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.ui.theme.Grey1
import com.imperatorofdwelling.android.presentation.ui.theme.h5_white
import java.util.Calendar

@Composable
fun Calendar(


    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier.then(modifier),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.calendar),
            tint = Grey1,
            contentDescription = stringResource(R.string.calendar),
        )
        Spacer(modifier = Modifier.width(10.dp))
        val calendar = Calendar.getInstance()
        val month = stringResource(
            id = calendar.get(Calendar.MONTH).monthToRes()
        ).substring(0..2)
        val dayOfWeek =
            stringResource(id = calendar.get(Calendar.DAY_OF_WEEK).toDayOfWeek())
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        Text(text = "$dayOfWeek, $day $month", style = h5_white)
    }
}

private fun Int.toDayOfWeek() = when (this) {
    Calendar.MONDAY -> R.string.monday
    Calendar.TUESDAY -> R.string.tuesday
    Calendar.WEDNESDAY -> R.string.wednesday
    Calendar.THURSDAY -> R.string.thursday
    Calendar.FRIDAY -> R.string.friday
    Calendar.SATURDAY -> R.string.saturday
    Calendar.SUNDAY -> R.string.sunday
    else -> R.string.saturday
}

private fun Int.monthToRes() = when (this) {
    0 -> R.string.january
    1 -> R.string.february
    2 -> R.string.march
    3 -> R.string.april
    4 -> R.string.may
    5 -> R.string.june
    6 -> R.string.july
    7 -> R.string.august
    8 -> R.string.september
    9 -> R.string.october
    10 -> R.string.november
    11 -> R.string.december
    else -> R.string.october
}