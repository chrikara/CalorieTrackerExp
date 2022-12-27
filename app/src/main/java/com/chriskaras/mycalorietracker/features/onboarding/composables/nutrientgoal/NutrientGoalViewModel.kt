package com.chriskaras.mycalorietracker.features.onboarding.composables.nutrientgoal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chriskaras.mycalorietracker.data.preferences.Preferences
import com.chriskaras.mycalorietracker.use_cases.FilterOutDigits
import com.chriskaras.mycalorietracker.util.UiEvent
import com.chriskaras.mycalorietracker.util.UiText
import com.chriskaras.mycalorietracker.util.navigate.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NutrientGoalViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterOutDigits: FilterOutDigits
) : ViewModel() {

    var selectedNutrient by mutableStateOf<NutrientGoalState>(NutrientGoalState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onMacroClick(macro : String, value : String){
        when (macro){
            "carbs" -> selectedNutrient = selectedNutrient.copy(carbsRatio = filterOutDigits(value))
            "proteins" -> selectedNutrient = selectedNutrient.copy(proteinRatio = filterOutDigits(value))
            "fats" -> selectedNutrient = selectedNutrient.copy(fatRatio = filterOutDigits(value))
        }
        println(selectedNutrient)
    }

    fun onNextClick() {
        viewModelScope.launch {
            val carbs = selectedNutrient.carbsRatio.toFloatOrNull() ?: 0f
            val proteins = selectedNutrient.proteinRatio.toFloatOrNull() ?: 0f
            val fats = selectedNutrient.fatRatio.toFloatOrNull() ?: 0f

            if(carbs+proteins+fats != 100f){
                _uiEvent.send(UiEvent.ShowSnackbar(
                    message = UiText.DynamicString("Not adding up to 100")
                ))

                return@launch
            }

            preferences.saveCarbRatio(carbs/100f)
            preferences.saveProteinRatio(proteins/100f)
            preferences.saveFatRatio(fats/100f)

            _uiEvent.send(UiEvent.Navigate(Route.TRACKER_OVERVIEW))
        }
    }

}