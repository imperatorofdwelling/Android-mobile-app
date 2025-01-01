package com.imperatorofdwelling.android.presentation.ui.stay_list_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.imperatorofdwelling.android.presentation.entities.Dwelling
import com.imperatorofdwelling.android.presentation.ui.apart_detail.ApartDetail
import com.imperatorofdwelling.android.presentation.ui.components.DwellingItem
import com.imperatorofdwelling.android.presentation.ui.components.buttons.BackButton
import com.imperatorofdwelling.android.presentation.ui.theme.largeDp
import org.koin.androidx.compose.koinViewModel

class StayListScreen(
    private val list: List<Dwelling>
): Screen {
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<StayListViewModel>()
        viewModel.updateList(list)
        val state = viewModel.state.collectAsState()
        Scaffold(
            topBar = { StayListTopBar() }
        ){ paddingValues ->
            StayListBody(
                stayList = state.value.listStay,
                paddingValues = paddingValues,
                onLikeItemClick = viewModel::onLikeClick,
                isImagesLoaded = state.value.isImagesLoaded
            )}
    }

    @Composable
    private fun StayListTopBar(){
        val navigator = LocalNavigator.currentOrThrow
        Row{
            BackButton(onClick = { navigator.pop() })
        }
    }
    @Composable
    private fun StayListBody(
        stayList: List<Dwelling>,
        paddingValues: PaddingValues,
        onLikeItemClick: suspend (String, Boolean) -> Boolean,
        isImagesLoaded: Boolean
    ){
        val navigator = LocalNavigator.currentOrThrow
        LazyColumn{
            items(
                stayList,
                key = { item -> "${item.id}${item.isLiked}$isImagesLoaded" }) { item ->
                DwellingItem(
                    item,
                    modifier = Modifier
                        .padding(horizontal = largeDp)
                        .clickable {
                            navigator.push(ApartDetail(item))
                        },
                    onLikeClick = onLikeItemClick,
                    imageModifier = Modifier.height(200.dp)
                )
                Spacer(modifier = Modifier.height(largeDp))
            }
        }
    }
}