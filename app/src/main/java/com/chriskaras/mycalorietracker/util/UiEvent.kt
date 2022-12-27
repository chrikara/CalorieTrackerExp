package com.chriskaras.mycalorietracker.util

sealed class UiEvent

// All the things we would like to do with Ui but only happen once and
// are not "state".

{

    data class Navigate(val route : String) : UiEvent()
    object NavigateUp : UiEvent()
    data class ShowSnackbar(val message : UiText) : UiEvent()

}
