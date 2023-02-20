package com.example.data.remote.response.admin

import com.example.domain.entity.admin.GetMessageRecordEntity
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID

data class GetMessageRecordResponse (
    @SerializedName("id") val id: UUID,
    @SerializedName("start_at") val startAt: String,
    @SerializedName("counsellor_name") val counsellorName: String,
    @SerializedName("customer_name") val customerName: String,
    @SerializedName("type") val type: String
)

fun GetMessageRecordResponse.toEntity() = GetMessageRecordEntity(
    id = id.toString(),
    startAt = LocalDateTime.parse(startAt).format(DateTimeFormatter.ofPattern("yyyy-MM-DD HH:MM")),
    counsellorName = counsellorName,
    customerName = customerName,
    type = type,
)
