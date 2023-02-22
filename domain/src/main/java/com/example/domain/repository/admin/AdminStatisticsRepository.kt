package com.example.domain.repository.admin

import com.example.domain.entity.admin.GetStatisticsEntity
import com.example.domain.entity.admin.StatisticsDetailEntity

interface AdminStatisticsRepository {
    suspend fun getStatisticsList(): List<GetStatisticsEntity>

    suspend fun getStatisticsDetail(id: String): StatisticsDetailEntity
}