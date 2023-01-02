package com.chriskaras.mycalorietracker.features.tracker.presentation.search

import com.chriskaras.mycalorietracker.features.tracker.domain.model.TrackableFood


data class TrackableFoodUiState(
    val food: TrackableFood,
    val isExpanded: Boolean = false,
    val amount: String = ""
)