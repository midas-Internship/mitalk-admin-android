package com.example.domain.usecase.admin

import com.example.domain.entity.admin.GetAllCounsellorEntity
import com.example.domain.repository.admin.AdminIssuedRepository
import javax.inject.Inject

class GetCounsellorListUseCase @Inject constructor(
    private val adminIssuedRepository: AdminIssuedRepository
) {
    suspend operator fun invoke() = kotlin.runCatching {
        adminIssuedRepository.getCounsellorList()
    }
}