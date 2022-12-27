package com.chriskaras.mycalorietracker.features.tracker.domain.use_cases

import com.chriskaras.mycalorietracker.features.tracker.domain.model.TrackableFood
import com.chriskaras.mycalorietracker.features.tracker.domain.repository.TrackerRepository



class SearchFood(
    private val repository: TrackerRepository
) {

    suspend operator fun invoke(
        query: String,
        page: Int = 1,
        pageSize: Int = 40
    ): Result<List<TrackableFood>> {
        if(query.isBlank()) {
            return Result.success(emptyList())
        }
        return repository.searchFood(query.trim(), page, pageSize)
    }
}