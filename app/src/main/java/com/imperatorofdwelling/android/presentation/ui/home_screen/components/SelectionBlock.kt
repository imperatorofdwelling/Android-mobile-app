package com.imperatorofdwelling.android.presentation.ui.home_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.ui.components.PrimaryButton
import com.imperatorofdwelling.android.presentation.ui.theme.h4_grey

@Composable
fun SelectionBlock(
    onClickTypeSelection: () -> Unit,
    onClickResidentsSelection: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, top = 16.dp)
    ) {
        Box(contentAlignment = Alignment.CenterStart, modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClickTypeSelection()
            }) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                painter = painterResource(R.drawable.type_of_dwelling),
                contentDescription = null
            )
            Text(
                modifier = Modifier.padding(start = 50.dp),
                text = stringResource(R.string.type_of_dwelling_you_need),
                style = h4_grey
            )
        }
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                painter = painterResource(R.drawable.dates),
                contentDescription = null
            )
            Text(
                modifier = Modifier.padding(start = 50.dp),
                text = stringResource(R.string.dates),
                style = h4_grey
            )
        }
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .clickable {
                    onClickResidentsSelection()
                }
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                painter = painterResource(R.drawable.residents),
                contentDescription = null
            )
            Text(
                modifier = Modifier.padding(start = 50.dp),
                text = stringResource(R.string.residents),
                style = h4_grey
            )
        }
        PrimaryButton(
            text = stringResource(R.string.apply),
            modifier = Modifier.padding(top = 8.dp),
        ){

        }
    }
}