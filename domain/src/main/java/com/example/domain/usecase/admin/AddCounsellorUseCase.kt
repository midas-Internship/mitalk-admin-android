package com.example.domain.usecase.admin

import com.example.domain.repository.admin.AdminIssuedRepository
import javax.inject.Inject

class AddCounsellorUseCase @Inject constructor(
    private val adminIssuedRepository: AdminIssuedRepository
) {
    suspend operator fun invoke(
        name: String
    ) = kotlin.runCatching {
        adminIssuedRepository.addCounsellor(name)
    }
}
