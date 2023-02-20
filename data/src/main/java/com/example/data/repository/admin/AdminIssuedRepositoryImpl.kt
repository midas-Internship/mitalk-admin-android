package com.example.data.repository.admin

import com.example.domain.entity.admin.GetAllCounsellorEntity
import com.example.domain.repository.admin.AdminIssuedRepository
import javax.inject.Inject

class AdminIssuedRepositoryImpl @Inject constructor(

): AdminIssuedRepository {
    override suspend fun getCounsellorList(): List<GetAllCounsellorEntity> = listOf()
}