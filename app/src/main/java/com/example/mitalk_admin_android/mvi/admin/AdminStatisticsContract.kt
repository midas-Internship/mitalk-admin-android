package com.example.mitalk_admin_android.mvi.admin

import com.example.domain.entity.admin.GetStatisticsEntity
import com.example.domain.entity.admin.StatisticsDetailEntity

data class AdminStatisticsState(
    val listStatistics: List<GetStatisticsEntity> = listOf(
        GetStatisticsEntity("이름", "id",3F)
    ),
    val reviewList: List<StatisticsDetailEntity.ReviewsData> = listOf(),
    val messageList: List<String> = listOf(),
)
