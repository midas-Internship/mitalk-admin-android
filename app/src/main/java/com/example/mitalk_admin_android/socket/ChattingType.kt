package com.example.mitalk_admin_android.socket

import com.example.mitalk_admin_android.util.toChatTime
import com.google.gson.annotations.SerializedName
import java.time.LocalTime

data class SocketType(
    @SerializedName("type")
    val type: String?,
)

data class SuccessRoom(
    @SerializedName("room_id")
    val roomId: String,
)

data class ChatData(
    @SerializedName("room_id")
    val roomId: String,
    @SerializedName("message_id")
    val messageId: String,
    @SerializedName("role")
    val role: String,
    @SerializedName("chat_message_type")
    val chatMessageType: String,
    @SerializedName("message")
    val message: String?,
)

fun ChatData.toUseData() = com.example.mitalk_admin_android.ui.chat.ChatData(
    id = messageId,
    text = message ?: "",
    isMe = role == "COUNSELLOR",
    time = LocalTime.now().toChatTime()
)

fun com.example.mitalk_admin_android.ui.chat.ChatData.toDeleteChatData(deleteMsg: String) =
    com.example.mitalk_admin_android.ui.chat.ChatData(
        id = id,
        text = deleteMsg,
        isMe = isMe,
        time = time
    )