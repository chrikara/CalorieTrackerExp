package com.chriskaras.mycalorietracker.features.onboarding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.chriskaras.mycalorietracker.ui.theme.DarkGreen
import com.chriskaras.mycalorietracker.ui.theme.LocalSpacing

@Composable
fun SelectableButton(
    modifier : Modifier = Modifier,
    isEnabled : Boolean = true,
    text : String,
    onClick : ()->Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(RoundedCornerShape(100.dp))
            .clickable {
                onClick()
            }
            .border(
                width = 2.dp,
                color = DarkGreen,
                shape = RoundedCornerShape(100.dp)
            )

            .background(
                color = if(isEnabled) DarkGreen else Color.Transparent,
                shape = RoundedCornerShape(100.dp)
            )

            .padding(LocalSpacing.current.spacingLarge)


    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            color = if(isEnabled) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.primary,
        )
    }

}