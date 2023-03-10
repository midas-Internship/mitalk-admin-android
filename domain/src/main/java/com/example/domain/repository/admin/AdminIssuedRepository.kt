package com.example.domain.repository.admin

import com.example.domain.entity.admin.GetAllCounsellorEntity

interface AdminIssuedRepository {
    suspend fun getCounsellorList(): List<GetAllCounsellorEntity>

    suspend fun addCounsellor(name: String)

    suspend fun deleteCounsellor(id: String)
}