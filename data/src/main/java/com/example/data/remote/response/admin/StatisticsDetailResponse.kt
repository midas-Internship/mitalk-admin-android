package com.example.data.remote.response.admin

import com.example.domain.entity.admin.StatisticsDetailEntity
import com.google.gson.annotations.SerializedName

data class StatisticsDetailResponse(
    @SerializedName("reviews") val reviews: List<ReviewData>,
    @SerializedName("messages") val messages: List<String>,
) {
    data class ReviewData(
        @SerializedName("review_item") val reviewItem: String,
        @SerializedName("percentage") val percentage: Float
    )
}

fun StatisticsDetailResponse.toEntity() = StatisticsDetailEntity(
    reviews = reviews.map { it.toData() },
    messages = messages
)
fun StatisticsDetailResponse.ReviewData.toData() = StatisticsDetailEntity.ReviewsData(
    reviewItem = reviewItem,
    percentage = percentage
)

