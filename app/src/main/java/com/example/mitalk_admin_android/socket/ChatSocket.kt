package com.example.mitalk_admin_android.socket

import com.example.mitalk_admin_android.BuildConfig
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import okhttp3.*
import org.json.JSONObject
import java.time.ZoneId
import java.util.SimpleTimeZone
import java.util.UUID

data class SocketType(
    val type: String?
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
    val message: String
)

class ChatSocket(
    successAction: () -> Unit = {},
    receiveAction: (String) -> Unit = {},
) {
    private lateinit var webSocket: WebSocket
    private lateinit var request: Request
    private lateinit var client: OkHttpClient
    private lateinit var receiveAction: (String) -> Unit
    private val listener: WebSocketListener

    init {
        listener = object : WebSocketListener() {
            override fun onMessage(webSocket: WebSocket, text: String) {
                super.onMessage(webSocket, text)
                val gson = Gson()
                when (gson.fromJson(text, SocketType::class.java).type) {
                    "SYSTEM_3_1" -> {
                        val result = gson.fromJson(text, SuccessRoom::class.java)
                        successAction()
                    }
                    null -> {
                        val data = gson.fromJson(text, ChatData::class.java)
                        val result = ChatData(
                            roomId = data.roomId,
                            messageId = data.messageId,
                            role = data.role,
                            chatMessageType = data.chatMessageType,
                            message = data.message
                        )
                        receiveAction(result.message)
                    }
                }
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                super.onFailure(webSocket, t, response)
                println("안녕 소켓 연결 안됨 $t")
            }
        }
    }

    fun send(roomId: String, text: String) {
        val data = JSONObject().apply {
            put("room_id", roomId)
            put("message_id", UUID.randomUUID())
            put("chat_message_type", "SEND")
            put("role", "COUNSELLOR")
            put("message", text)
        }

        webSocket.send(data.toString())
    }

    fun startSocket(accessToken: String) {
        client = OkHttpClient()
        request = Request.Builder()
            .addHeader("Authorization", "Bearer $accessToken")
            .url(BuildConfig.SOCKET_URL)
            .build()
        webSocket = client.newWebSocket(request, listener)
    }

    fun close() {
        webSocket?.close(1000, "Close")
        client.dispatcher.executorService.shutdown()
    }
}