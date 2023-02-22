package com.example.mitalk_admin_android.mvi.admin

import com.example.domain.entity.admin.GetAdminUserCareEntity

data class AdminUserCareState(
    val userList: List<GetAdminUserCareEntity> = listOf()
)

sealed class AdminUserCareSideEffect {

}