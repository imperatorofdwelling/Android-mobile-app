package com.imperatorofdwelling.android.presentation.ui.landlord.creating.components

import androidx.compose.foundation.background
import com.imperatorofdwelling.android.presentation.ui.theme.DarkGrey
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.ui.theme.h4_white
import com.imperatorofdwelling.android.presentation.ui.theme.largeDp

@Composable
fun AddressItem(
    name: String,
    isDefault: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.5.dp)
            .background(color = DarkGrey),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(largeDp))
        Text(name, style = h4_white, modifier = Modifier.weight(1f))
        if (isDefault) {
            Image(painterResource(id = R.drawable.select_mark), contentDescription = null)
            Spacer(modifier = Modifier.width(largeDp))
        }
    }
}