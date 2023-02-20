package com.example.domain.entity

import java.time.ZonedDateTime
import java.util.*

data class RecordEntity(
    val recordId: String,
    val type: String,
    val counsellorName: String,
    val customerName: String,
    val time: String,
)
