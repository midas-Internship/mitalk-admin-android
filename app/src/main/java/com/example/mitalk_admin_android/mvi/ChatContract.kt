package com.example.mitalk_admin_android.mvi

import android.net.Uri
import com.example.mitalk_admin_android.socket.ChatSocket
import com.example.mitalk_admin_android.ui.counsellor.chat.ChatData

data class ChatState(
    val customerName: String = "",
    val chatSocket: ChatSocket = ChatSocket(),
    val accessToken: String = "",
    val chatList: List<ChatData> = mutableListOf(),
    val uploadList: List<Uri> = mutableListOf(),
    val isOccupied: Boolean = false,
)

sealed class ChatSideEffect {
    data class ReceiveChat(val chat: ChatData, val chatSize: Int) : ChatSideEffect()
    data class ReceiveChatUpdate(val chat: ChatData) : ChatSideEffect()
    data class ReceiveChatDelete(val chatId: String) : ChatSideEffect()
    data class SuccessRoom(val name: String) : ChatSideEffect()
    data class SuccessUpload(val url: String) : ChatSideEffect()
    object FinishRoom : ChatSideEffect()
    data class FileSizeException(val uri: Uri) : ChatSideEffect()
    object FileOverException : ChatSideEffect()
    object FileNotAllowedException : ChatSideEffect()
}