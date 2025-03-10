package com.imperatorofdwelling.android.presentation.ui.favorites

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.ui.navigation.TabImperatorOfDwelling
import com.imperatorofdwelling.android.presentation.ui.theme.animation.slideAnimationDefault

object FavoritesTab: TabImperatorOfDwelling {

    override val iconSelected: @Composable () -> Painter = {
        painterResource(id = R.drawable.white_like)
    }

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
        Navigator(FavoritesScreen()){ navigator ->
            SlideTransition(
                navigator,
                animationSpec = slideAnimationDefault()
            )
        }
    }
}