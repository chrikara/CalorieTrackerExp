package com.chriskaras.mycalorietracker.features.tracker.domain.use_cases

import com.chriskaras.mycalorietracker.features.tracker.domain.model.MealType
import com.chriskaras.mycalorietracker.features.tracker.domain.model.TrackableFood
import com.chriskaras.mycalorietracker.features.tracker.domain.model.TrackedFood
import com.chriskaras.mycalorietracker.features.tracker.domain.repository.TrackerRepository
import java.time.LocalDate
import kotlin.math.roundToInt


class TrackFood(
    private val repository: TrackerRepository
) {

    suspend operator fun invoke(
        food: TrackableFood,
        amount : Int,
        mealType: MealType,
        date : LocalDate

    ){
      repository.insertTrackedFood(
          TrackedFood(
              name = food.name,
              carbs = ((food.carbsPer100g/100f) * amount).roundToInt(),
              protein = ((food.proteinPer100g/100f) * amount).roundToInt(),
              fat = ((food.fatPer100g/100f) * amount).roundToInt(),
              calories = ((food.caloriesPer100g/100f) * amount).roundToInt(),
              imageUrl = food.imageUrl,
              amount = amount,
              mealType = mealType,
              date = date,

          )
      )
    }
}