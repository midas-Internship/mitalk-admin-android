package com.example.mitalk_admin_android.mvi

import com.example.mitalk_admin_android.socket.ChatSocket

data class ChatState(
    val chatSocket: ChatSocket = ChatSocket(),
    val accessToken: String = "",
)

sealed class ChatSideEffect {

}