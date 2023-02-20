package com.example.data.remote.datasource.admin

import com.example.domain.entity.admin.GetAllCounsellorEntity

interface RemoteAdminIssuedDataSource {
    suspend fun getCounsellorList(): List<GetAllCounsellorEntity>

    suspend fun addCounsellor(name: String)
}