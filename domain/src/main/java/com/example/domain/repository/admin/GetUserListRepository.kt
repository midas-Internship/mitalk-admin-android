package com.example.domain.repository.admin

import com.example.domain.entity.admin.GetAdminUserCareEntity

interface GetUserListRepository {
    suspend fun getUserList(): List<GetAdminUserCareEntity>
}