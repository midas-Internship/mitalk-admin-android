package com.example.mitalk_admin_android.socket

import com.example.mitalk_admin_android.BuildConfig
import com.google.gson.Gson
import okhttp3.*
import java.time.ZoneId
import java.util.SimpleTimeZone
import java.util.UUID

data class SocketType(
    val type: String?
)

data class FailWaitingRoom(
    val message: String
)

data class WaitingRoom(
    val order: String,
    val message: String
)

data class SuccessRoom(
    val roomId: String,
)

data class ChatData(
    val roomId: String,
    val messageId: String,
    val role: String,
    val chatMessageType: String,
    val message: String,
    val sendTime: String
)

class ChatSocket(
    failAction: () -> Unit = {},
    waitingAction: (String) -> Unit = {},
    successAction: (String) -> Unit = {},
    receiveAction: (String) -> Unit = {},
) {
    private lateinit var webSocket: WebSocket
    private lateinit var request: Request
    private lateinit var client: OkHttpClient
    private val listener: WebSocketListener

    init {
        listener = object : WebSocketListener() {
            override fun onMessage(webSocket: WebSocket, text: String) {
                super.onMessage(webSocket, text)
                val gson = Gson()
                when (gson.fromJson(text, SocketType::class.java).type) {
                    "SYSTEM_1_1_1", "SYSTEM_1_2" -> {
                        val result = gson.fromJson(text, WaitingRoom::class.java)
                        waitingAction(result.order)
                    }
                    "SYSTEM_1_1_2" -> {
                        gson.fromJson(text, FailWaitingRoom::class.java)
                        failAction()
                    }
                    "SYSTEM_3_1" -> {
                        val result = gson.fromJson(text, SuccessRoom::class.java)
                        successAction(result.roomId)
                    }
                    null -> {
                        val data = gson.fromJson(text, ChatData::class.java)
                        val result = ChatData(
                            roomId = data.roomId,
                            messageId = data.messageId,
                            role = data.role,
                            chatMessageType = data.chatMessageType,
                            message = data.message,
                            sendTime = "SimpleTimeZone.getTimeZone(ZoneId.from())"
                        )
                        receiveAction(text)
                    }
                }
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                super.onFailure(webSocket, t, response)
                println("소켓 연결 안됨 $t")
            }
        }
    }

    fun send(roomId: String, text: String) {
        val data = ChatData(
            roomId = roomId,
            messageId = UUID.randomUUID().toString(),
            chatMessageType = "SEND",
            role = "CUSTOMER",
            message = text,
            sendTime = ""
        )
        webSocket.send(data.toString())
    }

    fun startSocket(chatType: String, accessToken: String) {
        client = OkHttpClient()
        request = Request.Builder()
            .addHeader("Authorization", "Bearer $accessToken")
            .addHeader("ChatType", chatType)
            .url(BuildConfig.SOCKET_URL)
            .build()
        webSocket = client.newWebSocket(request, listener)
    }

    fun close() {
        webSocket?.close(1000, "Close")
        client.dispatcher.executorService.shutdown()
    }
}