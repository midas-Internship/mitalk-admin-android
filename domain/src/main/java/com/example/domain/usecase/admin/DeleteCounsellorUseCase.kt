package com.example.domain.usecase.admin

import com.example.domain.repository.admin.AdminIssuedRepository
import javax.inject.Inject

class DeleteCounsellorUseCase @Inject constructor(
    private val adminIssuedRepository: AdminIssuedRepository
) {
    suspend operator fun invoke(
        id: String
    ) = kotlin.runCatching {
        adminIssuedRepository.deleteCounsellor(id)
    }
}