package com.example.mitalk_admin_android.mvi.admin

import com.example.domain.entity.admin.GetAdminMessageRecord
import com.example.domain.entity.admin.GetAdminUserCareEntity
import java.time.LocalDateTime
import java.util.UUID

data class AdminMessageRecordState(
    val recordList: List<GetAdminMessageRecord> = listOf(
        GetAdminMessageRecord(
            id = "",
            startAt = "2023.09.20 19:21",
            counsellorName = "이준서",
            customerName = "백승민",
            type = "FEEDBACK"
        ),
        GetAdminMessageRecord(
            id = "",
            startAt = "2023.09.20",
            counsellorName = "이준서",
            customerName = "백승민",
            type = "FEEDBACK"
        ),
        GetAdminMessageRecord(
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