package com.example.mitalk_admin_android.mvi.admin

import com.example.domain.entity.admin.GetStatisticsEntity

data class AdminStatisticsState(
    val listStatistics: List<GetStatisticsEntity> = listOf(
        GetStatisticsEntity("이름", "id",3F)
    )
)
