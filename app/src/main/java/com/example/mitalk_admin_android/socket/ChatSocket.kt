package com.example.mitalk_admin_android.socket

import com.example.mitalk_admin_android.BuildConfig
import com.google.gson.Gson
import okhttp3.*
import org.json.JSONObject

class ChatSocket(
    successAction: (String) -> Unit = {},
    finishAction: () -> Unit = {},
    receiveAction: (com.example.mitalk_admin_android.ui.counsellor.chat.ChatData) -> Unit = {},
    receiveActionUpdate: (com.example.mitalk_admin_android.ui.counsellor.chat.ChatData) -> Unit = {},
    receiveActionDelete: (String) -> Unit = {},
) {
    private lateinit var webSocket: WebSocket
    private lateinit var request: Request
    private lateinit var client: OkHttpClient
    private val listener: WebSocketListener
    private var roomId = ""

    init {
        listener = object : WebSocketListener() {
            override fun onMessage(webSocket: WebSocket, text: String) {
                super.onMessage(webSocket, text)
                val gson = Gson()
                when (gson.fromJson(text, SocketType::class.java).type) {
                    "SYSTEM_3_1" -> {
                        val result = gson.fromJson(text, SuccessRoom::class.java)
                        roomId = result.roomId
                        successAction(result.name)
                    }
                    null -> {
                        val result = gson.fromJson(text, ChatData::class.java)
                        when (result.chatMessageType) {
                            "SEND" -> {
                                receiveAction(result.toUseData())
                            }
                            "UPDATE" -> {
                                receiveActionUpdate(result.toUseData())
                            }
                            "DELETE" -> {
                                receiveActionDelete(result.messageId)
                            }
                            "END" -> {
                                finishAction()
                            }
                        }
                    }
                }
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                super.onFailure(webSocket, t, response)
                println("?????? ?????? ?????? ?????? $t")
            }
        }
    }

    fun send(
        messageId: String? = null,
        text: String? = null,
        messageType: String = "SEND",
    ) {
        val data = JSONObject().apply {
            put("room_id", roomId)
            if (messageId != null) put("message_id", messageId)
            put("chat_message_type", messageType)
            put("role", "COUNSELLOR")
            if (text != null) put("message", text)
        }

        webSocket.send(data.toString())
    }

    fun startSocket(accessToken: String) {
        client = OkHttpClient()
        request = Request.Builder()
            .addHeader("Authorization", "Bearer $accessToken")
            .addHeader("RoomId", "null")
            .url(BuildConfig.SOCKET_URL)
            .build()
        webSocket = client.newWebSocket(request, listener)
    }

    fun close() {
        if (::webSocket.isInitialized && ::client.isInitialized) {
            webSocket.close(1000, "Close")
            client.dispatcher.executorService.shutdown()
        }
    }
}