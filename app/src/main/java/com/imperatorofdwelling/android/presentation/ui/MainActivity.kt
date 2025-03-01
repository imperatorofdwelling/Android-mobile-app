package com.imperatorofdwelling.android.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.navigator.Navigator
import com.imperatorofdwelling.android.presentation.ui.components.LoadingUI
import com.imperatorofdwelling.android.presentation.ui.error.ErrorScreen
import com.imperatorofdwelling.android.presentation.ui.navigation.MainNavigation
import com.imperatorofdwelling.android.presentation.ui.sign_In.SignInScreen
import com.imperatorofdwelling.android.presentation.ui.theme.MyApplicationTheme
import com.imperatorofdwelling.android.presentation.ui.utils.LCE
import com.imperatorofdwelling.android.presentation.ui.utils.isError
import com.imperatorofdwelling.android.presentation.ui.utils.isLoading
import kotlinx.coroutines.delay
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

                var isLoadingTime by remember { mutableStateOf(true) }

                LaunchedEffect(Unit) {
                    delay(1500)
                    isLoadingTime = false
                }

                if (lce.value.isLoading() || isLoadingTime) {
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