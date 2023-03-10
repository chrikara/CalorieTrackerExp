package com.chriskaras.mycalorietracker.features.tracker.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.chriskaras.mycalorietracker.data.local.TrackerDao
import com.chriskaras.mycalorietracker.data.remote.OpenFoodApi
import com.chriskaras.mycalorietracker.features.tracker.data.mapper.toTrackableFood
import com.chriskaras.mycalorietracker.features.tracker.data.mapper.toTrackedFood
import com.chriskaras.mycalorietracker.features.tracker.data.mapper.toTrackedFoodEntity
import com.chriskaras.mycalorietracker.features.tracker.domain.model.TrackableFood
import com.chriskaras.mycalorietracker.features.tracker.domain.model.TrackedFood
import com.chriskaras.mycalorietracker.features.tracker.domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class TrackerRepositoryImpl(
    private val dao: TrackerDao,
    private val api: OpenFoodApi
): TrackerRepository {

    override suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>> {
        return try {
            val searchDto = api.searchFood(
                query = query,
                page = page,
                pageSize = pageSize
            )
            Result.success(
                searchDto.products.mapNotNull { it.toTrackableFood() }
            )
        } catch(e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun insertTrackedFood(food: TrackedFood) {
        dao.insertTrackedFood(food.toTrackedFoodEntity())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun deleteTrackedFood(food: TrackedFood) {
        dao.deleteTrackedFood(food.toTrackedFoodEntity())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getFoodsForDate(localDate: LocalDate): Flow<List<TrackedFood>> {
        return dao.getFoodsForDate(
            day = localDate.dayOfMonth,
            month = localDate.monthValue,
            year = localDate.year
        ).map { entities ->
            entities.map { it.toTrackedFood() }
        }
    }
}