package com.imperatorofdwelling.android.presentation.ui.my_objects

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.ui.home_screen.HomeScreen
import com.imperatorofdwelling.android.presentation.ui.navigation.TabImperatorOfDwelling
import com.imperatorofdwelling.android.presentation.ui.theme.animation.slideAnimationDefault

object MyObjectsTab: TabImperatorOfDwelling {
    private fun readResolve(): Any = MyObjectsTab

    override val iconSelected: @Composable () -> Painter =
        { painterResource(id = R.drawable.my_objects_filled) }

    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(R.string.my_objects)
            val icon =
                painterResource(id = R.drawable.my_objects)
            return remember {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        Navigator(HomeScreen()) { navigator ->
            SlideTransition(
                navigator,
                animationSpec = slideAnimationDefault()
            )
        }
    }
}