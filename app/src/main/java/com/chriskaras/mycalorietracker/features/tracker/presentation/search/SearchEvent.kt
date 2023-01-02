package com.chriskaras.mycalorietracker.features.tracker.presentation.search

import com.chriskaras.mycalorietracker.features.tracker.domain.model.MealType
import com.chriskaras.mycalorietracker.features.tracker.domain.model.TrackableFood
import java.time.LocalDate



    sealed class SearchEvent {
        data class OnQueryChange(val query: String) : SearchEvent()
        object OnSearch : SearchEvent()
        data class OnToggleTrackableFood(val food: TrackableFood) : SearchEvent()
        data class OnAmountForFoodChange(
            val food: TrackableFood,
            val amount: String
        ) : SearchEvent()
        data class OnTrackFoodClick(
            val food: TrackableFood,
            val mealType: MealType,
            val date: LocalDate
        ): SearchEvent()
        data class OnSearchFocusChange(val isFocused: Boolean): SearchEvent()
    }

