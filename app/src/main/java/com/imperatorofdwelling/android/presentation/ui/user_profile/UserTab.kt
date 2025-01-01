package com.imperatorofdwelling.android.presentation.ui.user_profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.ui.home_screen.HomeTab

object UserTab : Tab {
    private fun readResolve(): Any = UserTab
    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(R.string.profile)
            val icon = painterResource(id = R.drawable.profile)
            return remember {
                TabOptions(
                    index = 2u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        Navigator(screen = UserProfile())
    }
}