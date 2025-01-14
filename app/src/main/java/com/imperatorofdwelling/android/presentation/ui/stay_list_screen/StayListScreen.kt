package com.imperatorofdwelling.android.presentation.ui.stay_list_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.imperatorofdwelling.android.presentation.entities.Dwelling
import com.imperatorofdwelling.android.presentation.ui.apart_detail.ApartDetail
import com.imperatorofdwelling.android.presentation.ui.components.DwellingItem
import com.imperatorofdwelling.android.presentation.ui.components.buttons.BackButton
import com.imperatorofdwelling.android.presentation.ui.theme.DarkGrey
import com.imperatorofdwelling.android.presentation.ui.theme.h1
import com.imperatorofdwelling.android.presentation.ui.theme.largeDp
import org.koin.androidx.compose.koinViewModel

class StayListScreen(
    private val title: String,
    private val list: List<Dwelling>
) : Screen {
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<StayListViewModel>()
        viewModel.updateList(list)
        val state = viewModel.state.collectAsState()
        Scaffold(
            topBar = { StayListTopBar(title) }
        ) { paddingValues ->
            Spacer(modifier = Modifier.height(largeDp))
            StayListBody(
                stayList = state.value.listStay,
                modifier = Modifier.padding(paddingValues),
                onLikeItemClick = viewModel::onLikeClick,
                isImagesLoaded = state.value.isImagesLoaded
            )
        }
    }

    @Composable
    private fun StayListTopBar(title: String) {
        val navigator = LocalNavigator.currentOrThrow
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = DarkGrey),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(largeDp))
            BackButton(onClick = { navigator.pop() }, modifier = Modifier.padding(vertical = 10.dp))
            Spacer(modifier = Modifier.width(largeDp))
            Text(style = h1, text = title)
        }
    }

    @Composable
    private fun StayListBody(
        stayList: List<Dwelling>,
        onLikeItemClick: suspend (String, Boolean) -> Boolean,
        isImagesLoaded: Boolean,
        modifier: Modifier = Modifier
    ) {
        val navigator = LocalNavigator.currentOrThrow
        LazyColumn(modifier = modifier) {
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