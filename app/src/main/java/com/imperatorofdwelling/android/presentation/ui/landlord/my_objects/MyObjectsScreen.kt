package com.imperatorofdwelling.android.presentation.ui.landlord.my_objects

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.domain.stays.entities.Stay
import com.imperatorofdwelling.android.presentation.entities.Dwelling
import com.imperatorofdwelling.android.presentation.ui.components.buttons.ModeSwitch
import com.imperatorofdwelling.android.presentation.ui.favorites.components.TopAppBarFavourites
import com.imperatorofdwelling.android.presentation.ui.landlord.my_objects.components.Object
import org.koin.androidx.compose.koinViewModel

class MyObjectsScreen: Screen {
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<MyObjectsViewModel>()
        val state = viewModel.state.collectAsState()
        Scaffold(
            topBar = {
                TopAppBarFavourites(stringResource(R.string.my_objects))
            }
        ){ paddingValues ->
            MyObjectsBody(
                modifier = Modifier.padding(paddingValues).padding(horizontal = 16.dp),
                myObjects = state.value.myObjectsList,
                onModeChanged = viewModel::onModeChanged,
                selectedMode = state.value.selectedMode
            )
        }
    }

    @Composable
    fun MyObjectsBody(
        modifier: Modifier = Modifier,
        myObjects: List<Stay>,
        onModeChanged: (String) -> Unit,
        selectedMode: String,
    ){
        LazyColumn {
            item {
                val modes = listOf(stringResource(R.string.active),
                    stringResource(R.string.cancelled))
                ModeSwitch(
                    onRoleSelected = onModeChanged,
                    modes = modes,
                    selectedRole = selectedMode.ifEmpty { modes[0] }
                )
            }
            items(myObjects){ myObject ->
                Object(_object = myObject)
            }
        }
    }
}