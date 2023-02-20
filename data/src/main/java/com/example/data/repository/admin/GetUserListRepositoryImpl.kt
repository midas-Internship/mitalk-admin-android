package com.example.data.repository.admin

import com.example.domain.entity.admin.GetAdminUserCareEntity
import com.example.domain.repository.admin.GetUserListRepository
import javax.inject.Inject

class GetUserListRepositoryImpl @Inject constructor(

): GetUserListRepository {
    override suspend fun getUserList(): List<GetAdminUserCareEntity> =
        listOf<GetAdminUserCareEntity>()
}