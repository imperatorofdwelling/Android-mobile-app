package com.imperatorofdwelling.android.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import cafe.adriel.voyager.navigator.Navigator
import com.imperatorofdwelling.android.presentation.ui.navigation.MainNavigation
import com.imperatorofdwelling.android.presentation.ui.sign_In.SignInScreen
import com.imperatorofdwelling.android.presentation.ui.theme.MyApplicationTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val viewModel = koinViewModel<MainViewModel>()
                val state = viewModel.state.collectAsState()
                val skipRegistration = state.value.isAuthSkip
                val initialScreen = if(skipRegistration){
                    MainNavigation()
                } else {
                    SignInScreen()
                }
                Navigator(screen = initialScreen, onBackPressed = { false })
            }
        }
    }
}