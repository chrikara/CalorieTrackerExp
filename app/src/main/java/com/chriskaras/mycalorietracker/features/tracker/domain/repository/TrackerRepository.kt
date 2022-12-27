package com.chriskaras.mycalorietracker.features.tracker.domain.repository


import com.chriskaras.mycalorietracker.features.tracker.domain.model.TrackableFood
import com.chriskaras.mycalorietracker.features.tracker.domain.model.TrackedFood
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface TrackerRepository {

    suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>>

    suspend fun insertTrackedFood(food: TrackedFood)

    suspend fun deleteTrackedFood(food: TrackedFood)

    fun getFoodsForDate(localDate: LocalDate): Flow<List<TrackedFood>>
}