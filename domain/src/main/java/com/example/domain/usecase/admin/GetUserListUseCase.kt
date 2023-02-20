package com.example.domain.usecase.admin

import com.example.domain.entity.admin.GetAdminUserCareEntity
import com.example.domain.repository.admin.GetUserListRepository
import javax.inject.Inject

class GetUserListUseCase @Inject constructor(
    private val getUserListRepository: GetUserListRepository,
) {
    suspend operator fun invoke() =  kotlin.runCatching {
        getUserListRepository.getUserList()
    }
}