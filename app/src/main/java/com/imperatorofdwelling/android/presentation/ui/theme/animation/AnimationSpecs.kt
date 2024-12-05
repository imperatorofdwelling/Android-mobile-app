package com.imperatorofdwelling.android.presentation.ui.theme.animation

import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween

fun<T> animationSpecSlowly(): TweenSpec<T> {
    return tween(600)
}