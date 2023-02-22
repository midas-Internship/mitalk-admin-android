package com.example.domain.entity.admin

data class StatisticsDetailEntity(
    val reviews: List<ReviewsData>,
    val messages: List<String>,
) {
    data class ReviewsData(
        val reviewItem: String,
        val percentage: Float,
    )
}
