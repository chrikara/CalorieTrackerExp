package com.chriskaras.mycalorietracker.features.tracker.data.mapper

import com.chriskaras.mycalorietracker.data.remote.dto.Product
import com.chriskaras.mycalorietracker.features.tracker.domain.model.TrackableFood

fun Product.toTrackableFood() : TrackableFood? {

    return TrackableFood(
        name = productName ?: return null,
        imageUrl = imageFrontThumbUrl,
        caloriesPer100g = nutriments.energyKcal100g.toInt(),
        carbsPer100g = nutriments.carbohydrates100g.toInt(),
        proteinPer100g = nutriments.proteins100g.toInt(),
        fatPer100g = nutriments.fat100g.toInt()

    )
}