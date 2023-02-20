package com.example.mitalk_admin_android.mvi.admin

import com.example.domain.entity.admin.GetAllCounsellorEntity

data class AdminIssuedState(
    val counsellorList: List<GetAllCounsellorEntity> = listOf(
        GetAllCounsellorEntity(name = "홍길동", counsellorId = "f13981aa-adca-11ed-afa1-0242ac120002", status = "on"),
        GetAllCounsellorEntity(name = "이준서", counsellorId = "f13981aa-adca-11ed-afa1-0242ac120002", status = "off"),
        GetAllCounsellorEntity(name = "홍길동", counsellorId = "f13981aa-adca-11ed-afa1-0242ac120002", status = "counselling"),
    )
)

sealed class AdminIssuedSideEffect {

}