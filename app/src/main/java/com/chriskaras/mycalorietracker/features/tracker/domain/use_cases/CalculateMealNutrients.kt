package com.chriskaras.mycalorietracker.features.tracker.domain.use_cases

import com.chriskaras.mycalorietracker.data.preferences.Preferences
import com.chriskaras.mycalorietracker.features.tracker.domain.model.MealType
import com.chriskaras.mycalorietracker.features.tracker.domain.model.TrackedFood
import com.chriskaras.mycalorietracker.model.ActivityLevel
import com.chriskaras.mycalorietracker.model.Gender
import com.chriskaras.mycalorietracker.model.GoalType
import com.chriskaras.mycalorietracker.model.UserInfo
import kotlin.math.roundToInt

class CalculateMealNutrients(
    private val preferences: Preferences
) {

    operator fun invoke(trackedFoods: List<TrackedFood>): Result {
        val allNutrients = trackedFoods
            .groupBy { it.mealType }
            .mapValues { entry ->
                val foods = entry.value

                MealNutrients(
                    carbs = foods.sumOf { it.carbs },
                    protein = foods.sumOf { it.protein },
                    fat = foods.sumOf { it.fat },
                    calories = foods.sumOf { it.calories },
                    mealType = entry.key
                )
                }

        val totalCarbs = allNutrients.values.sumOf {it.carbs}
        val totalProtein = allNutrients.values.sumOf {it.protein}
        val totalFat = allNutrients.values.sumOf {it.fat}
        val totalCalories = allNutrients.values.sumOf {it.calories}

        val userInfo = preferences.loadUserInfo()
        val dailyCalories = dailyCaloryRequirement(userInfo)

        val carbsGoal = (dailyCalories * userInfo.carbRatio) / 4f
        val proteinGoal = (dailyCalories * userInfo.proteinRatio) / 4f
        val fatGoal = (dailyCalories * userInfo.fatRatio) / 9f

        return Result(
            carbsGoal = carbsGoal.roundToInt(),
            proteinGoal = proteinGoal.roundToInt(),
            fatGoal = fatGoal.roundToInt(),
            caloriesGoal = dailyCalories,
            totalCarbs = totalCarbs,
            totalProtein = totalProtein,
            totalFat = totalFat,
            totalCalories = totalCalories,
            mealNutrients = allNutrients
        )
    }


    private fun bmr(userInfo: UserInfo): Int {
        return when(userInfo.gender) {
            is Gender.Male -> {
                (66.47f + 13.75f * userInfo.weight +
                        5f * userInfo.height - 6.75f * userInfo.age).roundToInt()
            }
            is Gender.Female ->  {
                (665.09f + 9.56f * userInfo.weight +
                        1.84f * userInfo.height - 4.67 * userInfo.age).roundToInt()
            }
        }
    }

    private fun dailyCaloryRequirement(userInfo: UserInfo): Int {
        val activityFactor = when(userInfo.activityLevel) {
            is ActivityLevel.Low -> 1.2f
            is ActivityLevel.Medium -> 1.3f
            is ActivityLevel.High -> 1.4f
        }
        val caloryExtra = when(userInfo.goalType) {
            is GoalType.LoseWeight -> -500
            is GoalType.KeepWeight -> 0
            is GoalType.GainWeight -> 500
        }
        return (bmr(userInfo) * activityFactor + caloryExtra).roundToInt()
    }

    data class MealNutrients(
        val carbs: Int,
        val protein: Int,
        val fat: Int,
        val calories: Int,
        val mealType: MealType
    )



    data class Result(
        val carbsGoal: Int,
        val proteinGoal: Int,
        val fatGoal: Int,
        val caloriesGoal: Int,
        val totalCarbs: Int,
        val totalProtein: Int,
        val totalFat: Int,
        val totalCalories: Int,
        val mealNutrients: Map<MealType, MealNutrients>
    )
}