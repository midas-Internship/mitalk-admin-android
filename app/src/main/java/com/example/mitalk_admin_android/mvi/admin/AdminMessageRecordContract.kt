package com.example.mitalk_admin_android.mvi.admin

import com.example.domain.entity.admin.GetMessageRecordEntity

data class AdminMessageRecordState(
    val recordList: List<GetMessageRecordEntity> = listOf()
)

sealed class AdminMessageRecordSideEffect {

}