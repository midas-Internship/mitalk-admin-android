package com.example.domain.usecase.admin

import com.example.domain.repository.admin.AdminStatisticsRepository
import javax.inject.Inject

class GetStatisticsDetailUseCase @Inject constructor(
    private val adminStatisticsRepository: AdminStatisticsRepository
) {
    suspend operator fun invoke(
        id: String
    ) = kotlin.runCatching {
        adminStatisticsRepository.getStatisticsDetail(id)
    }
}