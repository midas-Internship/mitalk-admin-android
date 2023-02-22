package com.example.data.remote.response.admin

import com.example.domain.entity.admin.GetStatisticsEntity
import com.google.gson.annotations.SerializedName
import java.util.UUID

data class GetStatisticsResponse(
    @SerializedName("counsellor_statistics") val counsellorStatistics: List<StatisticsData>,
) {
    data class StatisticsData(
        @SerializedName("name") val name: String,
        @SerializedName("id") val id: UUID,
        @SerializedName("star") val star: Float
    )
}


fun GetStatisticsResponse.StatisticsData.toEntity() = GetStatisticsEntity(
    name = name,
    id = id.toString(),
    star = star,
)
