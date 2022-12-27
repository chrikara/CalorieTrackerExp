package com.chriskaras.mycalorietracker.features.onboarding.composables.goal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chriskaras.mycalorietracker.data.preferences.Preferences
import com.chriskaras.mycalorietracker.model.ActivityLevel
import com.chriskaras.mycalorietracker.model.Gender
import com.chriskaras.mycalorietracker.model.GoalType
import com.chriskaras.mycalorietracker.util.UiEvent
import com.chriskaras.mycalorietracker.util.navigate.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoalViewModel @Inject constructor(
    private val preferences: Preferences
): ViewModel() {

    var selectedGoalType by mutableStateOf<GoalType>(GoalType.LoseWeight)
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onGoalClick(goalType: GoalType) {
        selectedGoalType = goalType
    }

    fun onNextClick() {
        viewModelScope.launch {
            preferences.saveGoalType(selectedGoalType)
            _uiEvent.send(UiEvent.Navigate(Route.NUTRIENT_GOAL))
        }
    }
}