package com.imperatorofdwelling.android.presentation.ui.landlord.creating.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.ui.components.MainCheckBox
import com.imperatorofdwelling.android.presentation.ui.theme.h2
import com.imperatorofdwelling.android.presentation.ui.theme.h3

@Composable
fun Amenities(
    modifier: Modifier = Modifier,
    amenities: List<String>,
    amenitiesSelected: List<String>,
    onAmenityClicked: (String) -> Unit
) {
    Text(text = stringResource(id = R.string.amenities), style = h2)
    key(amenitiesSelected){
        amenities.forEach {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
                    .clickable { onAmenityClicked(it) },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = it, style = h3)
                MainCheckBox(modifier = Modifier.clickable { onAmenityClicked(it) }, checked = amenitiesSelected.contains(it))
            }
        }
    }
}