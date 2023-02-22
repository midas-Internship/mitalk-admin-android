package com.example.mitalk_admin_android.mvi.admin

import com.example.domain.entity.admin.GetStatisticsEntity
import com.example.domain.entity.admin.StatisticsDetailEntity

data class AdminStatisticsState(
    val listStatistics: List<GetStatisticsEntity> = listOf(
        GetStatisticsEntity("이름", "id", 4.0F)
    ),
    val reviewList: List<StatisticsDetailEntity.ReviewsData> = listOf(
        StatisticsDetailEntity.ReviewsData("KINDNESS",10.0F),
        StatisticsDetailEntity.ReviewsData("UNKINDNESS", 10.0F)
    ),
    val messageList: List<String> = listOf(),
)
