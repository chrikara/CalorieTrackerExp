package com.chriskaras.mycalorietracker.util

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.chriskaras.mycalorietracker.ui.theme.BrightGreen
import com.chriskaras.mycalorietracker.ui.theme.LocalSpacing


object ComposeDefaults {

    @Composable
    fun buttonColor() : ButtonColors {
        return ButtonDefaults
            .buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            )
    }

    @Composable
    fun buttonPaddingValues() : PaddingValues{
        val spacing = LocalSpacing.current
        return PaddingValues(
            horizontal = spacing.horizontal,
            vertical = spacing.vertical
        )
    }
}