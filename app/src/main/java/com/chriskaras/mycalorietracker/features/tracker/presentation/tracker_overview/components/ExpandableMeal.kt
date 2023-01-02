package com.chriskaras.mycalorietracker.features.tracker.presentation.tracker_overview.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.chriskaras.mycalorietracker.features.tracker.presentation.components.NutrientInfo
import com.chriskaras.mycalorietracker.features.tracker.presentation.components.UnitDisplayTracker
import com.chriskaras.mycalorietracker.features.tracker.presentation.tracker_overview.Meal
import com.chriskaras.mycalorietracker.ui.theme.LocalSpacing


@Composable
fun ExpandableMeal(
    meal: Meal,
    onToggleClick: () -> Unit,
    content: @Composable () -> Unit,
    modifier: Modifier
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onToggleClick() }
                .padding(spacing.spacingMedium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = meal.drawableRes),
                contentDescription = meal.name.asString(context)
            )
            Spacer(modifier = Modifier.width(spacing.spacingMedium))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = meal.name.asString(context),
                        style = MaterialTheme.typography.displayLarge
                    )
                    Icon(
                        imageVector = if (meal.isExpanded) {
                            Icons.Default.KeyboardArrowUp
                        } else Icons.Default.KeyboardArrowDown,
                        contentDescription = ""
                    )
                }
                Spacer(modifier = Modifier.height(spacing.spacingLarge))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    UnitDisplayTracker(
                        amount = meal.calories,
                        unit = "kcal",
                        amountTextSize = 25.sp,
                        unitColor = Color.Black,
                        amountColor = Color.Black
                    )
                    Row {
                        NutrientInfo(
                            name = "carbs",
                            amount = meal.carbs,
                            unit = "g"
                        )
                        Spacer(modifier = Modifier.width(spacing.spacingSmall))
                        NutrientInfo(
                            name = "protein",
                            amount = meal.protein,
                            unit = "g"
                        )
                        Spacer(modifier = Modifier.width(spacing.spacingSmall))
                        NutrientInfo(
                            name = "fat",
                            amount = meal.fat,
                            unit = "g"
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(spacing.spacingMedium))
        AnimatedVisibility(visible = meal.isExpanded) {
            content()
        }
    }
}