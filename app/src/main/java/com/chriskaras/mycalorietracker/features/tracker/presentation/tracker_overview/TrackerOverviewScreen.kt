package com.chriskaras.mycalorietracker.features.tracker.presentation.tracker_overview

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import com.chriskaras.mycalorietracker.R
import com.chriskaras.mycalorietracker.features.tracker.domain.model.MealType
import com.chriskaras.mycalorietracker.features.tracker.domain.model.TrackedFood
import com.chriskaras.mycalorietracker.features.tracker.presentation.components.DaySelector
import com.chriskaras.mycalorietracker.features.tracker.presentation.tracker_overview.components.AddButton
import com.chriskaras.mycalorietracker.features.tracker.presentation.tracker_overview.components.ExpandableMeal
import com.chriskaras.mycalorietracker.features.tracker.presentation.tracker_overview.components.NutrientsHeader
import com.chriskaras.mycalorietracker.features.tracker.presentation.tracker_overview.components.TrackedFoodItem
import com.chriskaras.mycalorietracker.ui.theme.LocalSpacing
import com.chriskaras.mycalorietracker.util.UiEvent
import java.time.LocalDate

@OptIn(ExperimentalCoilApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TrackerOverviewScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: TrackerOverviewViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current
    val state = viewModel.statee

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = spacing.spacingLarge)
    ) {
        item {
            NutrientsHeader(state = viewModel.statee)
            DaySelector(
                modifier = Modifier.fillMaxWidth(),
                date = state.date,
                onPreviousDayClick = {viewModel.onEvent(TrackerOverviewEvent.OnPreviousDayClick)},
                onNextDayClick = { viewModel.onEvent(TrackerOverviewEvent.OnNextDayClick) }
            )
            TrackedFoodItem(trackedFood = TrackedFood(

                name = "Salmon",
                carbs = 100,
                protein = 100,
                fat = 100,
                calories = 1700,
                imageUrl = "",
                mealType = MealType.fromString("breakfast"),
                amount = 2,
                date = LocalDate.now()
            ) , onDeleteClick = {   },
            modifier = Modifier.fillMaxWidth())

        }

        items(state.meals){meal ->
            ExpandableMeal(
                meal = meal,
                onToggleClick = { viewModel.onEvent(TrackerOverviewEvent.OnToggleMealClick(meal)) },
                content = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = spacing.spacingSmall)
                    ) {
                        state.trackedFoods.forEach { food ->
                            TrackedFoodItem(
                                trackedFood = food,
                                onDeleteClick = {
                                    viewModel.onEvent(
                                        TrackerOverviewEvent
                                            .OnDeleteTrackedFoodClick(food)
                                    )
                                }
                            )
                            Spacer(modifier = Modifier.height(spacing.spacingMedium))
                        }
                        AddButton(
                            text = "Βάλε ${meal.name.asString(context)}",
                            onClick = {
                                viewModel.onEvent(
                                    TrackerOverviewEvent.OnAddFoodClick(meal)
                                )
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
        }


    }
}