package com.imperatorofdwelling.android.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cafe.adriel.voyager.navigator.Navigator
import com.imperatorofdwelling.android.presentation.ui.sign_In.SignInScreen
import com.imperatorofdwelling.android.presentation.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Navigator(screen = SignInScreen(), onBackPressed = { false })
            }
        }
    }
}