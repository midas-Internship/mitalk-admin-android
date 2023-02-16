package com.example.domain.entity

import java.time.ZonedDateTime
import java.util.*

data class RecordEntity(
    val type: String,
    val name: String,
    val time: ZonedDateTime,
    val roomId: UUID
)
