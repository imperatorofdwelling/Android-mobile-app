package com.imperatorofdwelling.android.presentation.ui.review_stays

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.core.screen.Screen
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.domain.reviews.entities.Review as ReviewEntity
import com.imperatorofdwelling.android.presentation.entities.reviewListMock
import com.imperatorofdwelling.android.presentation.ui.apart_detail.components.Review
import com.imperatorofdwelling.android.presentation.ui.components.DefaultTopBar
import com.imperatorofdwelling.android.presentation.ui.components.ExtraLargeSpacer
import com.imperatorofdwelling.android.presentation.ui.components.text_fields.IconTextField
import com.imperatorofdwelling.android.presentation.ui.review_stays.components.SortButton
import com.imperatorofdwelling.android.presentation.ui.theme.h2
import com.imperatorofdwelling.android.presentation.ui.theme.largeDp

class ReviewScreen : Screen {
    @Composable
    override fun Content() {
        Scaffold(topBar = {
            DefaultTopBar(stringResource(R.string.all_reviews))
        }) { paddingValues ->
            ReviewScreenBody(
                modifier = Modifier.padding(paddingValues),
                reviews = reviewListMock
            )
        }
    }

    @Composable
    fun ReviewScreenBody(
        reviews: List<ReviewEntity>,
        modifier: Modifier = Modifier
    ) {
        val iconSearch = painterResource(id = R.drawable.search_icon_unfocused)
        val iconSearchFocused = painterResource(id = R.drawable.search_icon_focused)
        Column(modifier = modifier.padding(horizontal = largeDp)) {
            ExtraLargeSpacer()
            IconTextField(
                unfocusedIcon = iconSearch,
                focusedIcon = iconSearchFocused,
                placeholderText = stringResource(R.string.search_by_keywords)
            )
            ExtraLargeSpacer()
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("${reviews.size} ${stringResource(R.string.reviews)}", style = h2)
                SortButton(text = "Old ones") {

                }
            }
            ExtraLargeSpacer()
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(largeDp),
            ) {
                items(reviews) { review ->
                    Review(review = review)
                }
                item { Spacer(modifier = Modifier.height(largeDp)) }
            }
        }
    }
}