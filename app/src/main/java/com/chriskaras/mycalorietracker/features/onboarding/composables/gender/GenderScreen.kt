package com.chriskaras.mycalorietracker.features.onboarding.composables.gender

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.chriskaras.mycalorietracker.features.onboarding.components.ActionButton
import com.chriskaras.mycalorietracker.features.onboarding.components.SelectableButton
import com.chriskaras.mycalorietracker.model.Gender
import com.chriskaras.mycalorietracker.ui.theme.LocalSpacing
import com.chriskaras.mycalorietracker.util.UiEvent
import com.chriskaras.mycalorietracker.util.navigate.Route


@Composable
fun GenderScreen(
    onNavigate : (UiEvent.Navigate)->Unit,
    viewModel: GenderViewModel = hiltViewModel()
) {
    var isChecked = viewModel.selectedGender
    println(isChecked)

    val spacing = LocalSpacing.current
    LaunchedEffect(key1 = true) {
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
            .padding(spacing.spacingExtraLarge),
        contentAlignment = Alignment.Center
    ){
        Column(

            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Text(
                text = "What is your gender?",
                style = MaterialTheme.typography.displayLarge
            )

            Spacer(modifier = Modifier.height(spacing.spacingExtraLarge))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){


                SelectableButton(
                    text = "Male",
                    onClick = { viewModel.onGenderClick(Gender.Male) },
                    isEnabled = viewModel.selectedGender == Gender.Male
                )
                Spacer(modifier = Modifier.width(LocalSpacing.current.spacingExtraLarge))

                SelectableButton(
                    text = "Female",
                    onClick = { viewModel.onGenderClick(Gender.Female) },
                    isEnabled = viewModel.selectedGender == Gender.Female
                )


            }

        }
        ActionButton(
            buttonText = "Next",
            onClick = {
                viewModel.onNextClick()
            },
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }

}