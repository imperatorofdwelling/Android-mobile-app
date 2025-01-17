package com.imperatorofdwelling.android.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import cafe.adriel.voyager.navigator.tab.Tab

interface TabImperatorOfDwelling : Tab {

    val iconSelected: @Composable () -> Painter
}