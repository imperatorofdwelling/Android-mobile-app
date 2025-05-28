package com.imperatorofdwelling.android.presentation.ui.components.buttons

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.imperatorofdwelling.android.presentation.ui.theme.GreyStroke

@Composable
fun ModeSwitch(
    modes: List<String>,
    modifier: Modifier  = Modifier,
    selectedRole: String,
    onRoleSelected: (String) -> Unit
) {
    val roles = listOf("tenant", "landlord")
    val selectedIndex = selectedRole
    val transition = updateTransition(targetState = selectedIndex, label = "switch_transition")

    val width = LocalConfiguration.current.screenWidthDp - 35
    val indicatorOffset by transition.animateDp(label = "indicator_offset") { role ->
        if (role == modes[0]) 0.dp else (width/2).dp
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(45.dp)
            .clip(RoundedCornerShape(9.dp))
            .background(GreyStroke),
    ) {
        Box(
            modifier = Modifier
                .padding(top = 3.dp, start = 3.dp, end = 3.dp)
                .fillMaxWidth(0.5f)
                .height(38.dp)
                .offset(x = indicatorOffset)
                .clip(RoundedCornerShape(7.dp))
                .background(Color.Gray)
        )

        Row(
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            roles.forEachIndexed { index, role ->
                Text(
                    text = role,
                    color = Color.White,
                    modifier = Modifier
                        .clickable { onRoleSelected(role) }
                        .padding(horizontal = 50.dp, vertical = 10.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
