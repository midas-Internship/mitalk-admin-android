package com.example.mitalk_admin_android.mvi.admin

import com.example.domain.entity.admin.GetAllCounsellorEntity

data class AdminIssuedState(
    val counsellorList: List<GetAllCounsellorEntity> = listOf()
)

sealed class AdminIssuedSideEffect {
    object StateRefresh : AdminIssuedSideEffect()

    object DeleteSuccess : AdminIssuedSideEffect()
    object DeleteFail : AdminIssuedSideEffect()
}