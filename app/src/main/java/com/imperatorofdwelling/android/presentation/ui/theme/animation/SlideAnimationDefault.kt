package com.imperatorofdwelling.android.presentation.ui.theme.animation

import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween

fun<T> slideAnimationDefault(): TweenSpec<T> {
    return tween(450)
}