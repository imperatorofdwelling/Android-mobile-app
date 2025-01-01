package com.imperatorofdwelling.android.presentation.ui.user_profile

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import cafe.adriel.voyager.core.screen.Screen
import com.imperatorofdwelling.android.presentation.ui.theme.h4_white
import org.koin.androidx.compose.koinViewModel

class UserProfile : Screen {
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<UserProfileViewModel>()
        val state = viewModel.state.collectAsState()
        UserProfileBody(
            jwtText = state.value.jwt
        )
    }

    @Composable
    fun UserProfileBody(
        jwtText: String
    ){
        Text(text = jwtText, style = h4_white)
    }
}