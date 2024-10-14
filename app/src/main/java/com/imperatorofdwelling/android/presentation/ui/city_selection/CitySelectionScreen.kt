package com.imperatorofdwelling.android.presentation.ui.city_selection

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.ui.components.LargeSpacer
import com.imperatorofdwelling.android.presentation.ui.components.PrimaryTextField
import com.imperatorofdwelling.android.presentation.ui.theme.largeDp

class CitySelectionScreen : Screen {
    @Composable
    override fun Content() {

    }

    @Preview
    @Composable
    fun CitySelectionPreview() {
        //val navigator = LocalNavigator.currentOrThrow
        CitySelectionBody (
            onBackClick = {
            })
        //{
////            navigator.pop()
//        }
    }

    @Composable
    fun CitySelectionBody(onBackClick: () -> Unit) {
        Column(modifier = Modifier.fillMaxSize()) {
            LargeSpacer()
            Row(modifier = Modifier.fillMaxWidth()) {
                Spacer(Modifier.width(largeDp))
                Image(
                    modifier = Modifier.size(48.dp).clickable {
                        onBackClick()
                    },
                    imageVector = ImageVector.vectorResource(R.drawable.back_button),
                    contentDescription = stringResource(
                        R.string.button_back
                    ),
                    contentScale = ContentScale.Crop
                )

                PrimaryTextField(
                    hint = stringResource(R.string.enter_the_city_name),
                    value = "",
                    onValueChange = {
                        
                    }
                )
            }

        }
    }

}