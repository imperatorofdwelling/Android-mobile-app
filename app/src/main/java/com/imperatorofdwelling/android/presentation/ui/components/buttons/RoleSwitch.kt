package com.imperatorofdwelling.android.presentation.ui.components.buttons

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.imperatorofdwelling.android.presentation.ui.theme.GreyStroke

private const val LANDLORD_ROLE: Int = 1
private const val TENANT_ROLE: Int = 0

@Composable
fun RoleSwitch(
    modifier: Modifier  = Modifier,
    selectedRole: Int,
    onRoleSelected: (Int) -> Unit
) {
    val roles = listOf("tenant", "landlord")
    val selectedIndex = selectedRole
    val transition = updateTransition(targetState = selectedIndex, label = "switch_transition")

    val width = LocalConfiguration.current.screenWidthDp - 35
    val indicatorOffset by transition.animateDp(label = "indicator_offset") { role ->
        if (role == TENANT_ROLE) 0.dp else (width/2).dp
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp)
            .clip(RoundedCornerShape(9.dp))
            .background(GreyStroke)
            .then(modifier),
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
                        .clickable { onRoleSelected(index) }
                        .padding(horizontal = 50.dp, vertical = 10.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRoleSwitch() {
    var selectedRole by remember { mutableStateOf(0) }

    RoleSwitch(selectedRole = selectedRole) { newRole ->
        selectedRole = newRole
    }
}