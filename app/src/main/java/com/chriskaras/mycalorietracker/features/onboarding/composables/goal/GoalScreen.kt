package com.chriskaras.mycalorietracker.features.onboarding.composables.goal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.chriskaras.mycalorietracker.features.onboarding.components.ActionButton
import com.chriskaras.mycalorietracker.features.onboarding.components.SelectableButton
import com.chriskaras.mycalorietracker.model.ActivityLevel
import com.chriskaras.mycalorietracker.model.GoalType
import com.chriskaras.mycalorietracker.ui.theme.LocalSpacing
import com.chriskaras.mycalorietracker.util.UiEvent

@Composable
fun GoalScreen(
    onNavigate : (UiEvent.Navigate) -> Unit,
    viewModel: GoalViewModel = hiltViewModel()
) {

    val spacing = LocalSpacing.current
    
    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }    
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spacingLarge)
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "What is your goal?",
                style = MaterialTheme.typography.displayLarge
            )
            Spacer(modifier = Modifier.height(spacing.spacingExtraLarge))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SelectableButton(
                    text = "Lose",
                    isEnabled = viewModel.selectedGoalType is GoalType.LoseWeight,
                    onClick = {viewModel.onGoalClick(GoalType.LoseWeight)}
                )
                SelectableButton(
                    text = "Keep",
                    isEnabled = viewModel.selectedGoalType is GoalType.KeepWeight,
                    onClick = {viewModel.onGoalClick(GoalType.KeepWeight)}
                )
                SelectableButton(
                    text = "Gain",
                    isEnabled = viewModel.selectedGoalType is GoalType.GainWeight,
                    onClick = {viewModel.onGoalClick(GoalType.GainWeight)}
                )
            }
           
            
        }
        ActionButton(
            onClick = { viewModel.onNextClick() },
            buttonText = "Next",
            modifier = Modifier.align(BottomEnd)
        )
    }
   
    
    
}