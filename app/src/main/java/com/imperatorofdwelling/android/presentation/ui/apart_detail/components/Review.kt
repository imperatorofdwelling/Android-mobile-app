package com.imperatorofdwelling.android.presentation.ui.apart_detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.domain.reviews.entities.Review
import com.imperatorofdwelling.android.presentation.entities.User
import com.imperatorofdwelling.android.presentation.ui.components.LargeSpacer
import com.imperatorofdwelling.android.presentation.ui.components.TextExpended
import com.imperatorofdwelling.android.presentation.ui.theme.DarkGrey
import com.imperatorofdwelling.android.presentation.ui.theme.GreyStroke
import com.imperatorofdwelling.android.presentation.ui.theme.White
import com.imperatorofdwelling.android.presentation.ui.theme.XXLdp
import com.imperatorofdwelling.android.presentation.ui.theme.extraLargeDp
import com.imperatorofdwelling.android.presentation.ui.theme.h3
import com.imperatorofdwelling.android.presentation.ui.theme.mediumDp
import com.imperatorofdwelling.android.presentation.ui.theme.smallDp

@Composable
@Preview
fun ReviewPreviewLol() {
    Review(
        review =
        Review(
            user = User(R.drawable.example_hotel_image, "Ivan Shinkaruk"),
            mark = 4.0,
            description = "Lorem ipsum dolor sit emet Lorem ipsum dolor sit emet Lorem ipsum dolor sit emet Lorem ipsum dolor sit emet Lorem ipsum dolor sit emet Lorem ipsum dolor sit emet Lorem ipsum dolor sit emet"
        ),
        modifier = Modifier
            .width(280.dp)
            .height(210.dp)
    )

}


@Composable
fun Review(
    review: Review,

    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .border(width = 1.dp, color = GreyStroke, shape = RoundedCornerShape(smallDp))
            .background(
                color = DarkGrey,
                shape = RoundedCornerShape(8.dp)
            )
            .clip(RoundedCornerShape(8.dp))
            .padding(horizontal = extraLargeDp, vertical = XXLdp),
        contentAlignment = Alignment.Center,
    ) {
        Column(modifier = Modifier.fillMaxHeight()) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = review.user.imageId),
                    contentDescription = review.user.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(RoundedCornerShape(smallDp))
                        .size(48.dp)
                )
                Column(modifier = Modifier.padding(horizontal = mediumDp)) {
                    Text(text = review.user.name, style = h3)
                    Row {
                        for (i in 1..5) {
                            if (i <= review.mark) {
                                Icon(
                                    painter = painterResource(id = R.drawable.star_review),
                                    tint = Color.Yellow,
                                    contentDescription = null,
                                )
                            } else {
                                Icon(
                                    painter = painterResource(id = R.drawable.star_review),
                                    tint = White,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                }
            }
            LargeSpacer()
            if (review.description != null) {
                TextExpended(text = review.description, collapsedLength = 120)
            }
        }
    }
}