package com.example.domain.usecase.admin

import com.example.domain.repository.admin.AdminStatisticsRepository
import javax.inject.Inject

class GetStatisticsUseCase @Inject constructor(
    private val adminStatisticsRepository: AdminStatisticsRepository
) {
    suspend operator fun invoke() = kotlin.runCatching {
        adminStatisticsRepository.getStatisticsList()
    }
}