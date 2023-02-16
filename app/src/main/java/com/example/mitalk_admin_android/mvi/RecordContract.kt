package com.example.mitalk_admin_android.mvi

import com.example.domain.entity.RecordEntity

data class RecordState(
    val recordList: List<RecordEntity> = listOf()
)

sealed class RecordSideEffect {

}