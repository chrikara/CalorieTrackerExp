package com.chriskaras.mycalorietracker.features.onboarding.composables.activitylevel

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
import com.chriskaras.mycalorietracker.ui.theme.LocalSpacing
import com.chriskaras.mycalorietracker.util.UiEvent

@Composable
fun ActivityLevelScreen(
    onNavigate : (UiEvent.Navigate) -> Unit,
    viewModel: ActivityLevelViewModel = hiltViewModel()
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
                text = "What is your activity level?",
                style = MaterialTheme.typography.displayLarge
            )
            Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SelectableButton(
                    text = "Low",
                    isEnabled = viewModel.selectedActivityLevel is ActivityLevel.Low,
                    onClick = {viewModel.onActivityClick(ActivityLevel.Low)}
                )
                SelectableButton(
                    text = "Medium",
                    isEnabled = viewModel.selectedActivityLevel is ActivityLevel.Medium,
                    onClick = {viewModel.onActivityClick(ActivityLevel.Medium)}
                )
                SelectableButton(
                    text = "High",
                    isEnabled = viewModel.selectedActivityLevel is ActivityLevel.High,
                    onClick = {viewModel.onActivityClick(ActivityLevel.High)}
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