package com.chriskaras.mycalorietracker.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp



data class Dimensions(

    val default : Dp = 0.dp,
    val spacingExtraSmall : Dp = 2.dp,
    val spaceSmall : Dp = 4.dp,
    val spaceMedium : Dp = 8.dp,
    val spacingLarge : Dp = 12.dp,
    val spaceExtraSmall : Dp = 16.dp,
    val horizontal : Dp = 16.dp,
    val vertical : Dp = 16.dp
)

val LocalSpacing = compositionLocalOf { Dimensions() }



