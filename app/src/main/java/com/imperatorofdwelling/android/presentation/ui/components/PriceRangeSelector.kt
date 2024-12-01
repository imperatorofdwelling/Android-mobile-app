import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Preview
@Composable
@ExperimentalGraphicsApi
fun PriceRangeSelector() {
    var priceRangeStart by remember { mutableStateOf(0f) }
    var priceRangeEnd by remember { mutableStateOf(100f) }
    val histogramData = listOf(2, 5, 10, 20, 15, 10, 5, 3, 2, 1, 3, 2, 1) // Пример данных гистограммы

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text("Price range", color = Color.White)

        Spacer(modifier = Modifier.height(16.dp))

        Box(modifier = Modifier.fillMaxWidth()) {
            Histogram(histogramData)
            RangeSlider(
                value = priceRangeStart..priceRangeEnd,
                onValueChange = {

                },
                modifier = Modifier.fillMaxWidth(),
                colors = SliderDefaults.colors(
                    thumbColor = Color.White,
                    activeTrackColor = Color.White,
                    inactiveTrackColor = Color.Gray
                )
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("From: ${priceRangeStart.roundToInt()}", color = Color.White)
            Text("To: ${priceRangeEnd.roundToInt()}", color = Color.White)
        }
    }
}

@Composable
fun Histogram(data: List<Int>) {
    val maxCount = data.maxOrNull() ?: 1

    Canvas(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)) {
        val barWidth = size.width / data.size

        data.forEachIndexed { index, count ->
            val barHeight = (size.height * (count / maxCount.toFloat()))
            drawRoundRect(
                color = Color.Gray,
                topLeft = androidx.compose.ui.geometry.Offset(x = index * barWidth, y = size.height - barHeight),
                size = androidx.compose.ui.geometry.Size(width = barWidth - 4.dp.toPx(), height = barHeight),
                cornerRadius = androidx.compose.ui.geometry.CornerRadius(4.dp.toPx())
            )
        }
    }
}
