package com.chriskaras.mycalorietracker.util.navigate

import androidx.navigation.NavController
import com.chriskaras.mycalorietracker.util.UiEvent

fun NavController.navigate(event : UiEvent.Navigate){
    this.navigate(event.route)
}