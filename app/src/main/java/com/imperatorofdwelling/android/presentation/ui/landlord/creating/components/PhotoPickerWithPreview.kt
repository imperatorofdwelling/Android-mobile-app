package com.imperatorofdwelling.android.presentation.ui.landlord.creating.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.HapticFeedbackConstantsCompat
import androidx.core.view.ViewCompat
import coil3.compose.AsyncImage
import com.imperatorofdwelling.android.R
import com.imperatorofdwelling.android.presentation.ui.components.buttons.PrimaryButton
import com.imperatorofdwelling.android.presentation.ui.landlord.creating.CreatingScreen
import com.imperatorofdwelling.android.presentation.ui.theme.Black
import com.imperatorofdwelling.android.presentation.ui.theme.GreyStroke
import com.imperatorofdwelling.android.presentation.ui.theme.Transparent
import com.imperatorofdwelling.android.presentation.ui.theme.XXLdp
import com.imperatorofdwelling.android.presentation.ui.theme.extraLargeDp
import com.imperatorofdwelling.android.presentation.ui.theme.title
import sh.calvin.reorderable.ReorderableItem
import sh.calvin.reorderable.rememberReorderableLazyGridState

@Composable
fun PhotoPickerWithPreview(
    modifier: Modifier = Modifier,
    imageUris: List<Uri>,
    onImageSelected: (Uri) -> Unit,
    onImageCancel: (Uri) -> Unit,
    onReorder: (List<Uri>) -> Unit,
    onContinueClick: () -> Unit,
    isComplete: Boolean
) {
    val view = LocalView.current
    val lazyGridState = rememberLazyGridState()
    val reorderableLazyListState = rememberReorderableLazyGridState(lazyGridState) { from, to ->
        val newList = imageUris.toMutableList().apply {
            add(to.index - 1, removeAt(from.index - 1))
        }
        onReorder(newList)
        ViewCompat.performHapticFeedback(
            view,
            HapticFeedbackConstantsCompat.SEGMENT_FREQUENT_TICK
        )
    }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents(),
        onResult = { uris ->
            uris.forEach { uri ->
                onImageSelected(uri)
            }
        }
    )
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    Box(modifier = modifier) {
        Spacer(modifier = Modifier
            .height(24.dp)
            .align(Alignment.TopCenter))
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxHeight()
                .align(Alignment.TopCenter),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            state = lazyGridState,
            columns = GridCells.Fixed(3)
        ) {
            item {
                Box(
                    modifier = Modifier
                        .size(90.dp)
                        .border(1.5.dp, color = GreyStroke, shape = RoundedCornerShape(8.dp))
                        .clip(RoundedCornerShape(8.dp))
                        .background(Transparent)
                        .clickable {
                            imagePickerLauncher.launch("image/*")
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Image(painter = painterResource(id = R.drawable.add), contentDescription = null)
                }
            }

            items(imageUris, key = { it }) { uri ->
                ReorderableItem(
                    state = reorderableLazyListState,
                    key = uri,
                ) { isDragging ->
                    val elevation by animateDpAsState(if (isDragging) 4.dp else 0.dp, label = "")
                    Surface(shadowElevation = elevation) {
                        AsyncImage(
                            model = uri,
                            contentDescription = null,
                            modifier = Modifier
                                .draggableHandle(
                                    onDragStarted = {
                                        ViewCompat.performHapticFeedback(
                                            view,
                                            HapticFeedbackConstantsCompat.GESTURE_START
                                        )
                                    },
                                    onDragStopped = {
                                        ViewCompat.performHapticFeedback(
                                            view,
                                            HapticFeedbackConstantsCompat.GESTURE_END
                                        )
                                    })
                                .size(90.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .draggableHandle()
                                .clickable {
                                    selectedImageUri = uri
                                }
                        )
                    }
                }
            }
        }

        PrimaryButton(
            enabled = isComplete,
            modifier = Modifier.align(Alignment.BottomCenter),
            text = stringResource(id = R.string.continue_string)
        ) {
            onContinueClick()
        }
        if (selectedImageUri != null) {
            BottomSheetCancel(
                onCancelRequest = { onImageCancel(selectedImageUri!!) },
                onDismissRequest = { selectedImageUri = null }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetCancel(
    onCancelRequest: () -> Unit,
    onDismissRequest: () -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        containerColor = Black
    ) {
        Column(modifier = Modifier.padding(horizontal = extraLargeDp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.manage_image),
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
            PrimaryButton(text = stringResource(R.string.remove_from_the_list)) {
                onCancelRequest()
                onDismissRequest()
            }
            Spacer(modifier = Modifier.height(XXLdp))
        }
    }
}

@Preview
@Composable
fun CreatingPreview() {
    CreatingScreen().Content()
}