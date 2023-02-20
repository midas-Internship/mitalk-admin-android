package com.example.domain.repository.admin

import com.example.domain.entity.admin.GetAllCounsellorEntity

interface AdminIssuedRepository {
    suspend fun getCounsellorList(): List<GetAllCounsellorEntity>
}