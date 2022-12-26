package com.chriskaras.mycalorietracker.features.onboarding.composables.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.chriskaras.mycalorietracker.R
import com.chriskaras.mycalorietracker.features.onboarding.components.ActionButton
import com.chriskaras.mycalorietracker.ui.theme.LocalSpacing
import com.chriskaras.mycalorietracker.util.UiEvent
import com.chriskaras.mycalorietracker.util.navigate.Route

@Composable
fun WelcomeScreen(
    onNavigate : (UiEvent.Navigate) -> Unit
) {

    val spacing = LocalSpacing.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)

        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.hello_lets),
            style = MaterialTheme.typography.displayLarge,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(spacing.spacingLarge))

        ActionButton(
            buttonText = stringResource(id = R.string.lets_go),
            onClick = {
                      onNavigate(UiEvent.Navigate(Route.AGE))
            },
            modifier = Modifier
                .align(CenterHorizontally)
        )



    }
}