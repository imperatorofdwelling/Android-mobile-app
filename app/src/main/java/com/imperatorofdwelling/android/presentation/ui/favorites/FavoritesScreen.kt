package com.imperatorofdwelling.android.presentation.ui.favorites

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.entities.Dwelling
import com.imperatorofdwelling.android.presentation.ui.components.buttons.PrimaryButton
import com.imperatorofdwelling.android.presentation.ui.components.buttons.StrokeButton
import com.imperatorofdwelling.android.presentation.ui.favorites.components.FavoriteDwellingGroup
import com.imperatorofdwelling.android.presentation.ui.favorites.components.TopAppBarFavourites
import com.imperatorofdwelling.android.presentation.ui.home_screen.HomeTab
import com.imperatorofdwelling.android.presentation.ui.stay_list_screen.StayListScreen
import com.imperatorofdwelling.android.presentation.ui.theme.Black
import com.imperatorofdwelling.android.presentation.ui.theme.XXLdp
import com.imperatorofdwelling.android.presentation.ui.theme.extraLargeDp
import com.imperatorofdwelling.android.presentation.ui.theme.h1
import com.imperatorofdwelling.android.presentation.ui.theme.h4_white
import com.imperatorofdwelling.android.presentation.ui.theme.largeDp
import com.imperatorofdwelling.android.presentation.ui.theme.smallDp
import com.imperatorofdwelling.android.presentation.ui.theme.title
import com.imperatorofdwelling.android.presentation.ui.utils.LCE
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer
import org.koin.androidx.compose.koinViewModel

class FavoritesScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = koinViewModel<FavoritesViewModel>()
        LaunchedEffect(Unit) {
            viewModel.refreshScreen()
        }

        val lce = viewModel.lce.collectAsState()
        val state = viewModel.state.collectAsState()

        Scaffold(
            topBar = {
                TopAppBarFavourites(stringResource(id = R.string.my_favorites))
            }
        ) { paddingValues ->
            if(lce.value == LCE.Loading) {
                FavoritesPlaceholder(modifier = Modifier.padding(paddingValues))
            } else {
                FavoritesBody(
                    favoriteGroups = state.value.favoriteGroups,
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }
    }


    @Composable
    private fun FavoritesPlaceholder(
        modifier: Modifier = Modifier
    ) {
        val shimmer = rememberShimmer(
            shimmerBounds = ShimmerBounds.Window,
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .then(modifier),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            repeat(7) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .shimmer(shimmer)
                        .background(
                            color = Color.Gray.copy(alpha = 0.3f),
                            shape = RoundedCornerShape(largeDp)
                        )
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun FavoritesBody(
        favoriteGroups: Map<String, List<Dwelling>>?,
        modifier: Modifier = Modifier
    ) {


        val tabNavigator = LocalTabNavigator.current
        val navigator = LocalNavigator.currentOrThrow
        var showBottomSheetMenu by remember { mutableStateOf(false) }
        Column(modifier = modifier) {
            if (!favoriteGroups.isNullOrEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .padding(horizontal = extraLargeDp)
                ) {
                    items(favoriteGroups.keys.toList()) { item ->
                        Spacer(modifier = Modifier.height(largeDp))
                        FavoriteDwellingGroup(
                            groupName = item,
                            countOption = favoriteGroups[item]?.size ?: 0,
                            modifier = Modifier.clickable {
                                navigator.push(
                                    StayListScreen(
                                        title = item,
                                        favoriteGroups[item] ?: emptyList()
                                    )
                                )
                            }
                        ) {
                            showBottomSheetMenu = true
                        }
                    }
                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = extraLargeDp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = stringResource(R.string.your_favorites_list_is),
                                style = h1
                            )
                            Text(
                                text = stringResource(R.string.empty),
                                style = h1
                            )
                            Spacer(modifier = Modifier.height(smallDp))
                            Text(
                                text = stringResource(R.string.your_top_picks_will_be_shown_here),
                                style = h4_white
                            )
                        }
                        Spacer(modifier = Modifier.height(XXLdp))
                        PrimaryButton(
                            text = stringResource(R.string.let_s_search),
                            modifier = Modifier.width(168.dp)
                        ) {
                            tabNavigator.current = HomeTab
                        }
                    }
                }
            }
            if (showBottomSheetMenu) {
                ModalBottomSheet(
                    onDismissRequest = { showBottomSheetMenu = false },
                    containerColor = Black
                ) {
                    Column(modifier = Modifier.padding(horizontal = extraLargeDp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = stringResource(id = R.string.manage_group),
                                style = title
                            )
                            Image(
                                painterResource(id = R.drawable.cross),
                                contentDescription = stringResource(
                                    R.string.close_the_modal_bottom_sheet
                                )
                            )
                        }
                        Spacer(modifier = Modifier.height(extraLargeDp))
                        PrimaryButton(text = stringResource(R.string.rename)) {

                        }
                        Spacer(modifier = Modifier.height(extraLargeDp))
                        StrokeButton(text = stringResource(R.string.remove_from_the_list)) {

                        }
                        Spacer(modifier = Modifier.height(XXLdp))
                    }
                }
            }
        }
    }
}
