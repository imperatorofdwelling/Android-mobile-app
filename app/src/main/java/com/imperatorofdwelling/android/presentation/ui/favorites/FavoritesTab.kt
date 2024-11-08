package com.imperatorofdwelling.android.presentation.ui.favorites

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.imperatorofdwelling.android.R

object FavoritesTab: Tab {
    private fun readResolve(): Any = FavoritesTab
    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(R.string.favorites)
            val icon =
                painterResource(id = R.drawable.favorites)
            return remember {
                TabOptions(
                    index = 1u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        Navigator(FavoritesScreen())
    }
}