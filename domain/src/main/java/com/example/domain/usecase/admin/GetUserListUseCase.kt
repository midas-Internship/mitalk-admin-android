package com.example.domain.usecase.admin

import com.example.domain.entity.admin.GetAdminUserCareEntity
import javax.inject.Inject

class GetUserListUseCase @Inject constructor(

) {
    suspend operator fun invoke() =  kotlin.runCatching {
        return@runCatching listOf<GetAdminUserCareEntity>()
    }
}