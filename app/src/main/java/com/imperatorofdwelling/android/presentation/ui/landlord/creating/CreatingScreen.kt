package com.imperatorofdwelling.android.presentation.ui.landlord.creating

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen

class CreatingScreen : Screen {
    @Composable
    override fun Content() {
        Scaffold(
            topBar = {
                TopBar()
            },
        ) { paddingValues ->
            Body(modifier = Modifier.padding(paddingValues))
        }
    }

    @Composable
    private fun TopBar(
        modifier: Modifier = Modifier
    ) {

    }

    @Composable
    private fun Body(
        modifier: Modifier = Modifier
    ) {

    }
}