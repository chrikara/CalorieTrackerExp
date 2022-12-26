package com.chriskaras.mycalorietracker.features.onboarding.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import com.chriskaras.mycalorietracker.ui.theme.LocalSpacing

@Composable
fun UnitTextField(
    value: String,
    onValueChange: (String) -> Unit,
    unit: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle(
        color = MaterialTheme.colorScheme.primary,
        fontSize = 70.sp
    ),
) {
    val spacing = LocalSpacing.current
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = textStyle,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            singleLine = true,
            modifier = Modifier
                .width(IntrinsicSize.Min) // Equivalent to Wrap Content
                .alignBy(LastBaseline) // Puts both texts at the bottom of the row
        )
        Spacer(modifier = Modifier.width(spacing.spacingSmall))
        Text(
            text = unit,
            modifier = Modifier.alignBy(LastBaseline)
        )
    }
}