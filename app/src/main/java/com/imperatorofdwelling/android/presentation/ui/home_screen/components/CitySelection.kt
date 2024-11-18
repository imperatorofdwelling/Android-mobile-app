package com.imperatorofdwelling.android.presentation.ui.home_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.imperatorofdwelling.android.presentation.ui.components.SmallSpacer
import com.imperatorofdwelling.android.presentation.ui.theme.extraLargeDp

@Composable
fun CitySelection(
    searchResults: List<String>,
    defaultCityName: String,
    onCityClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = extraLargeDp)
    ) {
        SmallSpacer()
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (defaultCityName.isNotBlank()) {
                item {
                    CityItem(
                        name = defaultCityName,
                        isDefault = true,
                        modifier = Modifier.clickable { onCityClick(defaultCityName) })
                }
            }
            items(searchResults) { cityName ->
                if (cityName != defaultCityName) {
                    CityItem(
                        name = cityName,
                        isDefault = false,
                        modifier = Modifier
                            .clickable {
                                onCityClick(cityName)
                            }
                            .animateItem()
                    )
                }
            }
        }
    }
}
