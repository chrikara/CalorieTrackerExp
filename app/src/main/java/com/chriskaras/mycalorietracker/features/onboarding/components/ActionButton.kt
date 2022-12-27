package com.chriskaras.mycalorietracker.features.onboarding.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.chriskaras.mycalorietracker.ui.theme.LocalSpacing
import com.chriskaras.mycalorietracker.util.ComposeDefaults

@Composable
fun ActionButton(
    modifier : Modifier = Modifier,
    onClick : () -> Unit,
    buttonText : String,
    textStyle: TextStyle = MaterialTheme.typography.labelMedium,
    isEnabled : Boolean = true
) {

    val spacing = LocalSpacing.current
    Button(
        modifier = modifier,
        enabled = isEnabled,
        shape = RoundedCornerShape(100.dp),
        onClick = onClick,
        colors = ComposeDefaults.buttonColor(),
        contentPadding = ComposeDefaults.buttonPaddingValues(),

    )

    {
        Text(
            text = buttonText,
            style = textStyle,
            modifier = Modifier.padding(spacing.spacingExtraSmall)
        )



    }

}