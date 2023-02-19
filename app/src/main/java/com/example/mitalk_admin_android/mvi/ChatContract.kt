package com.example.mitalk_admin_android.mvi

import com.example.mitalk_admin_android.socket.ChatSocket
import com.example.mitalk_admin_android.ui.chat.ChatData

data class ChatState(
    val chatSocket: ChatSocket = ChatSocket(),
    val accessToken: String = "",
)

sealed class ChatSideEffect {
    data class ReceiveChat(val chat: ChatData): ChatSideEffect()
    data class ReceiveChatUpdate(val chat: ChatData): ChatSideEffect()
    data class ReceiveChatDelete(val chatId: String): ChatSideEffect()
    data class SuccessRoom(val roomId: String): ChatSideEffect()
}