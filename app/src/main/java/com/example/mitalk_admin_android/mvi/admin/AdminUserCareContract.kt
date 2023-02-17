package com.example.mitalk_admin_android.mvi.admin

import com.example.domain.entity.admin.GetAdminUserCareEntity

data class AdminUserCareState(
    val userList: List<GetAdminUserCareEntity> = listOf(
        GetAdminUserCareEntity(
            "07432eca-add4-11ed-afa1-0242ac120002",
            "기철수",
            "helloworld@gmail.com",
            "28f467aa-add4-11ed-afa1-0242ac120002"
        ),
        GetAdminUserCareEntity(
            "07432eca-add4-11ed-afa1-0242ac120002",
            "기철수",
            "helloworld@gmail.com",
            null
        )
    )
)

sealed class AdminUserCareSideEffect {

}