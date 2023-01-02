package com.chriskaras.mycalorietracker.features.tracker.presentation.search

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chriskaras.mycalorietracker.features.tracker.domain.use_cases.TrackerUseCases
import com.chriskaras.mycalorietracker.use_cases.FilterOutDigits
import com.chriskaras.mycalorietracker.util.UiEvent
import com.chriskaras.mycalorietracker.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val trackerUseCases: TrackerUseCases,
    private val filterOutDigits: FilterOutDigits
): ViewModel() {

    var state by mutableStateOf(SearchState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event : SearchEvent){

        when(event){
            is SearchEvent.OnToggleTrackableFood -> {
                state = state.copy(
                    trackableFood = state.trackableFood.map { trackableFoodUiState ->
                        if(event.food == trackableFoodUiState.food){

                            trackableFoodUiState.copy(isExpanded = !trackableFoodUiState.isExpanded)
                        } else trackableFoodUiState

                    }
                )
            }

            is SearchEvent.OnAmountForFoodChange -> {
                state = state.copy(
                    trackableFood = state.trackableFood.map { trackableFoodUiState ->
                       if(event.food == trackableFoodUiState.food){

                           trackableFoodUiState.copy(amount = filterOutDigits(event.amount))
                       } else trackableFoodUiState

                    }
                )
            }
            is SearchEvent.OnQueryChange -> {
                state = state.copy(
                    query = event.query
                )
            }
            is SearchEvent.OnSearch -> {

            }
            is SearchEvent.OnSearchFocusChange -> {
                state = state.copy(
                    isHintVisible = !event.isFocused && state.query.isBlank()
                )
            }
            is SearchEvent.OnTrackFoodClick -> {
                trackFood(event)
            }
        }
    }
    private fun trackFood(event: SearchEvent.OnTrackFoodClick) {
        viewModelScope.launch {
            val uiState = state.trackableFood.find { it.food == event.food }
            trackerUseCases.trackFood(
                food = uiState?.food ?: return@launch,
                amount = uiState.amount.toIntOrNull() ?: return@launch,
                mealType = event.mealType,
                date = event.date
            )
            _uiEvent.send(UiEvent.NavigateUp)
        }
    }

    private fun executeSearch() {
        viewModelScope.launch {
            state = state.copy(
                isSearching = true,
                trackableFood = emptyList()
            )
            trackerUseCases
                .searchFood(state.query)
                .onSuccess { foods ->
                    state = state.copy(
                        trackableFood = foods.map {
                            TrackableFoodUiState(it)
                        },
                        isSearching = false,
                        query = ""
                    )
                }
                .onFailure {
                    state = state.copy(isSearching = false)
                    _uiEvent.send(
                        UiEvent.ShowSnackbar(
                            UiText.DynamicString("Error, something went wrong")
                        )
                    )
                }
        }
    }
}



