package com.chriskaras.mycalorietracker.features.onboarding.composables.age

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.chriskaras.mycalorietracker.features.onboarding.components.SelectableButton
import com.chriskaras.mycalorietracker.ui.theme.LocalSpacing


@Composable
fun AgeScreen() {
    var isChecked by remember {
        mutableStateOf(1)
    }
   
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            SelectableButton(text = "Male", onClick = {isChecked=1}, isEnabled = isChecked==1)
            Spacer(modifier = Modifier.width(LocalSpacing.current.spacingExtraLarge))
            SelectableButton(text = "Female", onClick = {isChecked=2},isEnabled = isChecked==2)
            Spacer(modifier = Modifier.width(LocalSpacing.current.spacingExtraLarge))
            SelectableButton(text = "Other",onClick = {isChecked=3},isEnabled = isChecked==3)
        }
        
    }
}