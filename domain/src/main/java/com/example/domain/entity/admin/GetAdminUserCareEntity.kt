package com.example.domain.entity.admin

data class GetAdminUserCareEntity(
    val id: String,
    val name: String,
    val email: String,
    val session: String?
)
