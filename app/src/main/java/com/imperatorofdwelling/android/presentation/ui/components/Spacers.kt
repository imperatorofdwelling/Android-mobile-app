package com.imperatorofdwelling.android.presentation.ui.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.imperatorofdwelling.android.presentation.ui.theme.extraLargeDp
import com.imperatorofdwelling.android.presentation.ui.theme.extraSmallDp
import com.imperatorofdwelling.android.presentation.ui.theme.largeDp
import com.imperatorofdwelling.android.presentation.ui.theme.mediumDp
import com.imperatorofdwelling.android.presentation.ui.theme.smallDp

@Composable
fun ColumnScope.ExtraSmallSpacer() = Spacer(modifier = Modifier.height(extraSmallDp))

@Composable
fun ColumnScope.SmallSpacer() = Spacer(modifier = Modifier.height(smallDp))

@Composable
fun ColumnScope.MediumSpacer() = Spacer(modifier = Modifier.height(mediumDp))

@Composable
fun ColumnScope.LargeSpacer() = Spacer(modifier = Modifier.height(largeDp))

@Composable
fun ColumnScope.ExtraLargeSpacer() = Spacer(modifier = Modifier.height(extraLargeDp))

@Composable
fun RowScope.ExtraSmallSpacer() = Spacer(modifier = Modifier.width(extraSmallDp))

@Composable
fun RowScope.SmallSpacer() = Spacer(modifier = Modifier.width(smallDp))

@Composable
fun RowScope.MediumSpacer() = Spacer(modifier = Modifier.width(mediumDp))

@Composable
fun RowScope.LargeSpacer() = Spacer(modifier = Modifier.width(largeDp))

@Composable
fun RowScope.ExtraLargeSpacer() = Spacer(modifier = Modifier.width(extraLargeDp))
