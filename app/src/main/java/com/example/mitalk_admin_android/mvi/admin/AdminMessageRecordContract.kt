package com.example.mitalk_admin_android.mvi.admin

import com.example.domain.entity.admin.GetMessageRecordEntity

data class AdminMessageRecordState(
    val recordList: List<GetMessageRecordEntity> = listOf(
        GetMessageRecordEntity(
            id = "",
            startAt = "2023.09.20 19:21",
            counsellorName = "이준서",
            customerName = "백승민",
            type = "FEEDBACK"
        ),
        GetMessageRecordEntity(
            id = "",
            startAt = "2023.09.20",
            counsellorName = "이준서",
            customerName = "백승민",
            type = "FEEDBACK"
        ),
        GetMessageRecordEntity(
            id = "",
            startAt = "2023.09.20",
            counsellorName = "이준서",
            customerName = "백승민",
            type = "FEEDBACK"
        )
    )
)

sealed class AdminMessageRecordSideEffect {

}