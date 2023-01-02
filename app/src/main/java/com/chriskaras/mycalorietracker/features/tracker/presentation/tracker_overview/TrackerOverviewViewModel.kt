package com.chriskaras.mycalorietracker.features.tracker.presentation.tracker_overview

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chriskaras.mycalorietracker.data.preferences.Preferences
import com.chriskaras.mycalorietracker.features.tracker.domain.use_cases.TrackerUseCases
import com.chriskaras.mycalorietracker.util.UiEvent
import com.chriskaras.mycalorietracker.util.navigate.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class TrackerOverviewViewModel @Inject constructor(
    private val preferences: Preferences,
    private val trackerUseCases: TrackerUseCases,
): ViewModel() {

    var statee by mutableStateOf(TrackerOverviewState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var getFoodsForDateJob: Job? = null


    init {
        preferences.saveShouldShowOnboarding(false)
    }

    fun onChangeDate(string : String){
        when(string){
            "plus" -> statee = statee.copy( date = statee.date.plusDays(1))
            "minus" -> statee = statee.copy( date = statee.date.minusDays(1))
        }
    }
    fun onEvent(event : TrackerOverviewEvent) {
        when (event){
            is TrackerOverviewEvent.OnAddFoodClick -> {
                viewModelScope.launch {
                    _uiEvent.send(
                        UiEvent.Navigate(
                            route = Route.SEARCH
                                    + "/${event.meal.mealType.name}"
                                    + "/${statee.date.dayOfMonth}"
                                    + "/${statee.date.monthValue}"
                                    + "/${statee.date.year}"
                        )
                    )
                }
            }

            is TrackerOverviewEvent.OnDeleteTrackedFoodClick -> {
                viewModelScope.launch {
                    trackerUseCases.deleteTrackedFood(event.trackedFood)
                    refreshFoods()
                }
            }
            TrackerOverviewEvent.OnNextDayClick -> {
                statee = statee.copy(
                    date = statee.date.plusDays(1)
                )
                refreshFoods()
            }
            TrackerOverviewEvent.OnPreviousDayClick -> {
                statee = statee.copy(
                    date = statee.date.minusDays(1)
                )
                refreshFoods()
            }
            is TrackerOverviewEvent.OnToggleMealClick -> {

                    statee = statee.copy(
                        meals = statee.meals.map {
                            if(it.name == event.meal.name){
                                it.copy(isExpanded = !it.isExpanded)
                            }else it
                        }
                    )
            }
        }
    }

    private fun refreshFoods(){
        getFoodsForDateJob?.cancel()
        getFoodsForDateJob = trackerUseCases.getFoodsForDate(statee.date)
            .onEach { foods ->
                val allNutrients = trackerUseCases.calculateMealNutrients(foods)

                statee = statee.copy(
                    caloriesGoal = allNutrients.caloriesGoal,
                    carbsGoal = allNutrients.carbsGoal,
                    proteinGoal = allNutrients.proteinGoal,
                    fatGoal = allNutrients.fatGoal,
                    totalCalories = allNutrients.totalCalories,
                    totalCarbs = allNutrients.totalCarbs,
                    totalProtein = allNutrients.totalProtein,
                    totalFat = allNutrients.totalFat,
                    date = statee.date,
                    trackedFoods = foods,
                    meals = statee.meals.map {
                        val nutrientsForMeal =
                            allNutrients.mealNutrients[it.mealType]
                                ?: return@map it.copy(
                                    carbs = 0,
                                    protein = 0,
                                    fat = 0,
                                    calories = 0
                                )
                        it.copy(
                            carbs = nutrientsForMeal.carbs,
                            protein = nutrientsForMeal.protein,
                            fat = nutrientsForMeal.fat,
                            calories = nutrientsForMeal.calories
                        )
                    }
                )
            }.launchIn(viewModelScope)
    }
}

