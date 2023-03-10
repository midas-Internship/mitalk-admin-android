package com.example.mitalk_admin_android.mvi

import com.example.domain.entity.RecordDetailEntity

data class RecordDetailState(
    val startAt: String = "",
    val customerName: String = "",
    val counsellorName: String = "",
    val messageRecords: List<MessageRecordData> = listOf(),
    val totalFindResultList: List<Int> = listOf(),
    val currentFindPosition: Int = 0
) {
    data class MessageRecordData(
        val messageId: String,
        val sender: String,
        val isFile: Boolean,
        val isDeleted: Boolean,
        val isUpdated: Boolean,
        val dataMap: List<MessageData>,
    ) {
        data class MessageData(
            val message: String,
            val time: String
        )
    }
}

fun RecordDetailEntity.MessageRecord.toSateData() = RecordDetailState.MessageRecordData(
    messageId = messageId,
    sender = sender,
    isFile = isFile,
    isDeleted = isDeleted,
    isUpdated = isUpdated,
    dataMap = dataMap.map { it.toStateData() }
)

fun RecordDetailEntity.MessageRecord.MessageData.toStateData() =
    RecordDetailState.MessageRecordData.MessageData(
        message = message,
        time = time
    )

sealed class RecordDetailSideEffect {
    data class ChangeCurrentFindPosition(val list: List<Int>, val scrollPosition: Int) :
        RecordDetailSideEffect()
}