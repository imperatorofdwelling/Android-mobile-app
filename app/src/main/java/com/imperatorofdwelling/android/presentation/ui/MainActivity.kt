package com.imperatorofdwelling.android.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.imperatorofdwelling.android.presentation.ui.home_screen.HomeTab
import com.imperatorofdwelling.android.presentation.ui.theme.DarkGrey
import com.imperatorofdwelling.android.presentation.ui.theme.MyApplicationTheme
import com.imperatorofdwelling.android.presentation.ui.theme.Transparent
import com.imperatorofdwelling.android.presentation.ui.theme.White
import com.imperatorofdwelling.android.presentation.ui.theme.h5
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                TabNavigator(HomeTab) {
                    Scaffold(
                        content = { contentPadding ->
                            Box(
                                modifier = Modifier
                                    .padding(contentPadding)
                                    .fillMaxSize()
                            ) {
                                CurrentTab()
                            }
                        },
                        bottomBar = {
                            NavigationBar(
                                contentColor = Transparent,
                                containerColor = DarkGrey,
                                modifier = Modifier.fillMaxHeight(0.08f)
                            ) {
                                TabNavigationItem(HomeTab)
                            }
                        }
                    )
                }
            }
        }
    }

    @Composable
    private fun RowScope.TabNavigationItem(tab: Tab) {
        val tabNavigator = LocalTabNavigator.current
        NavigationBarItem(
            selected = tabNavigator.current.key == tab.key,
            onClick = { tabNavigator.current = tab },
            icon = {
                Icon(
                    painter = tab.options.icon!!,
                    contentDescription = tab.options.title
                )
            },
            label = {
                Text(text = tab.options.title, style = h5)
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = White,
                unselectedIconColor = Transparent,
                selectedTextColor = White,
                unselectedTextColor = White,
                indicatorColor = Transparent
            )
        )
    }
}