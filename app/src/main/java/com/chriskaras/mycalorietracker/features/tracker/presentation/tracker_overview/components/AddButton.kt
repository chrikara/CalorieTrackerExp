package com.chriskaras.mycalorietracker.features.tracker.presentation.tracker_overview.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.chriskaras.mycalorietracker.ui.theme.LocalSpacing

@Composable
fun AddButton(
    modifier : Modifier = Modifier,
    color : Color = MaterialTheme.colorScheme.secondary,
    text : String,
    onClick: () -> Unit,
    style : TextStyle = MaterialTheme.typography.displayMedium
) {

    val spacing = LocalSpacing.current


        Row(
            modifier = modifier
                .clip(RoundedCornerShape(30.dp))
                .clickable(onClick = onClick)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.secondary,
                    shape = RoundedCornerShape(30.dp)
                )
                .padding(10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "",
                tint = color
            )
            Spacer(modifier = Modifier.width(spacing.spaceExtraSmall))
            Text(
                text = text,
                color = color,
                style = style,
                fontWeight = FontWeight.W500
            )
        }







}