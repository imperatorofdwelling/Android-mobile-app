package com.imperatorofdwelling.android.presentation.ui.landlord.creating

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.core.screen.Screen
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.ui.components.DefaultTopBar
import com.imperatorofdwelling.android.presentation.ui.landlord.creating.components.Help
import org.koin.androidx.compose.koinViewModel

class CreatingScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<CreatingViewModel>()
        val state = viewModel.state.collectAsState()
        Scaffold(
            topBar = {
                AnimatedVisibility(visible = state.value.isInitialScreen) {
                    DefaultTopBar(title = "")
                }
                AnimatedVisibility(visible = !state.value.isInitialScreen) {
                    DefaultTopBar(title = stringResource(R.string.creating_an_advert))
                }
            }
        ) { paddingValues ->
            if (state.value.isInitialScreen) {
                Help(
                    modifier = Modifier.padding(paddingValues),
                )
            }
        }
    }

}


