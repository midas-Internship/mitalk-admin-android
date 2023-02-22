package com.example.data.remote.datasource.admin

import com.example.domain.entity.admin.GetStatisticsEntity
import com.example.domain.entity.admin.StatisticsDetailEntity

interface RemoteAdminStatisticsDataSource {
    suspend fun getStatisticsList(): List<GetStatisticsEntity>

    suspend fun getStatisticsDetail(id: String): StatisticsDetailEntity
}