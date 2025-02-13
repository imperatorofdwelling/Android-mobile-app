package com.imperatorofdwelling.android.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import cafe.adriel.voyager.navigator.Navigator
import com.imperatorofdwelling.android.presentation.ui.components.LoadingUI
import com.imperatorofdwelling.android.presentation.ui.error.ErrorScreen
import com.imperatorofdwelling.android.presentation.ui.navigation.MainNavigation
import com.imperatorofdwelling.android.presentation.ui.sign_In.SignInScreen
import com.imperatorofdwelling.android.presentation.ui.theme.MyApplicationTheme
import com.imperatorofdwelling.android.presentation.ui.utils.LCE
import com.imperatorofdwelling.android.presentation.ui.utils.isError
import com.imperatorofdwelling.android.presentation.ui.utils.isLoading
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val viewModel = koinViewModel<AuthViewModel>()
                val state = viewModel.state.collectAsState()
                val lce = viewModel.lce.collectAsState()
                val skipRegistration = state.value.isAuthSkip
                if (lce.value.isLoading()) {
                    LoadingUI()
                } else if (lce.value.isError()) {
                    ErrorScreen(
                        throwable = (lce.value as LCE.Error).throwable,
                        onRetry = viewModel::tryAgainClick
                    ).Content()
                } else {
                    val initialScreen = if (skipRegistration) {
                        MainNavigation()
                    } else {
                        SignInScreen()
                    }
                    Navigator(screen = initialScreen, onBackPressed = { false })
                }
            }
        }
    }
}