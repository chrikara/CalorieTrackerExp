package com.chriskaras.mycalorietracker.features.onboarding.composables.nutrientgoal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.chriskaras.mycalorietracker.features.onboarding.components.ActionButton
import com.chriskaras.mycalorietracker.features.onboarding.components.UnitTextField
import com.chriskaras.mycalorietracker.ui.theme.LocalSpacing
import com.chriskaras.mycalorietracker.util.UiEvent

@Composable
fun NutrientGoalScreen(
    snackbarHostState: SnackbarHostState,
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: NutrientGoalViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigate(event)
                is UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                }
                else -> Unit
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spacingLarge)
    )
    {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "What are your nutrient goals?",
                style = MaterialTheme.typography.displayLarge
            )

            Spacer(modifier = Modifier.height(spacing.spacingLarge))

            UnitTextField(
                value = viewModel.selectedNutrient.carbsRatio,
                onValueChange ={carbs ->
                    viewModel.onMacroClick("carbs", carbs)

                },
                unit = "carbs"
            )
            Spacer(modifier = Modifier.height(spacing.spacingLarge))

            UnitTextField(
                value = viewModel.selectedNutrient.proteinRatio,
                onValueChange ={proteins ->
                    viewModel.onMacroClick("proteins", proteins)

                },
                unit = "proteins"
            )
            Spacer(modifier = Modifier.height(spacing.spacingLarge))

            UnitTextField(
                value = viewModel.selectedNutrient.fatRatio,
                onValueChange ={fats ->
                    viewModel.onMacroClick("fats", fats)

                },
                unit = "fats"
            )
            Spacer(modifier = Modifier.height(spacing.spacingLarge))

        }

        ActionButton(
            onClick = { viewModel.onNextClick() },
            buttonText = "Next",
            modifier = Modifier.align(Alignment.BottomEnd)
        )

    }





















}