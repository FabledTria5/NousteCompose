package com.fabledt5.noustecompose.presentation.utils

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColorInt

fun Gradient.toVerticalGradient() = Brush.verticalGradient(
    colors = listOf(
        Color(startColor.toColorInt()),
        Color(endColor.toColorInt())
    )
)

fun Gradient.toHorizontalGradient() = Brush.horizontalGradient(
    colors = listOf(
        Color(startColor.toColorInt()),
        Color(endColor.toColorInt())
    )
)