package com.chriskaras.mycalorietracker.features.tracker.presentation.tracker_overview.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.chriskaras.mycalorietracker.R
import com.chriskaras.mycalorietracker.features.tracker.domain.model.TrackedFood
import com.chriskaras.mycalorietracker.features.tracker.presentation.components.NutrientInfo
import com.chriskaras.mycalorietracker.ui.theme.LocalSpacing


@ExperimentalCoilApi
@Composable
fun TrackedFoodItem(
    trackedFood: TrackedFood,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .padding(spacing.spacingExtraSmall)
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(5.dp)
            )
            .background(MaterialTheme.colorScheme.surface)
            .padding(end = spacing.spaceMedium)
            .height(100.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberImagePainter(
                data = trackedFood.imageUrl,
                builder = {
                    crossfade(true)
                    error(R.drawable.ic_burger)
                    fallback(R.drawable.ic_burger)
                }
            ),
            contentDescription = trackedFood.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f)
                .clip(
                    RoundedCornerShape(
                        topStart = 5.dp,
                        bottomStart = 5.dp
                    )
                )
        )
        Spacer(modifier = Modifier.width(spacing.spaceMedium))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = trackedFood.name,
                style = MaterialTheme.typography.displayMedium,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )
            Spacer(modifier = Modifier.height(spacing.spacingExtraSmall))
            Text(
                text = stringResource(
                    id = R.string.nutrient_info,
                    trackedFood.amount,
                    trackedFood.calories
                ),
                style = MaterialTheme.typography.displayMedium
            )
        }
        Spacer(modifier = Modifier.width(spacing.spaceMedium))
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                modifier = Modifier
                    .align(Alignment.End)
                    .clickable { onDeleteClick() },
                contentDescription = ""
            )

            Spacer(modifier = Modifier.height(spacing.spaceSmall))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                NutrientInfo(
                    name = "carbs",
                    amount = trackedFood.carbs,
                    unit = "g",
                    amountTextSize = 16.sp,
                    unitTextSize = 12.sp,
                    nameTextStyle = MaterialTheme.typography.displayMedium
                )
                Spacer(modifier = Modifier.width(spacing.spaceSmall))
                NutrientInfo(
                    name = "protein",
                    amount = trackedFood.protein,
                    unit = "g",
                    amountTextSize = 16.sp,
                    unitTextSize = 12.sp,
                    nameTextStyle = MaterialTheme.typography.displayMedium
                )
                Spacer(modifier = Modifier.width(spacing.spaceSmall))
                NutrientInfo(
                    name = "fat",
                    amount = trackedFood.fat,
                    unit = "g",
                    amountTextSize = 16.sp,
                    unitTextSize = 12.sp,
                    nameTextStyle = MaterialTheme.typography.displayMedium
                )
            }
        }
    }
}