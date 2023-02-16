package com.example.data.remote.response

import com.example.domain.entity.RecordEntity
import com.google.gson.annotations.SerializedName
import java.time.ZonedDateTime
import java.util.UUID

data class RecordResponse(
    @SerializedName("type") val type: String,
    @SerializedName("name") val name: String,
    @SerializedName("time") val time: ZonedDateTime,
    @SerializedName("room_id") val roomId: UUID,
)

internal fun RecordResponse.toEntity() = RecordEntity(
    type = type,
    name = name,
    time = time,
    roomId = roomId
)
