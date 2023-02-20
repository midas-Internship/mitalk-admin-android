package com.example.mitalk_admin_android.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.auth.GetAccessTokenUseCase
import com.example.domain.usecase.file.PostFileUseCase
import com.example.mitalk_admin_android.mvi.ChatSideEffect
import com.example.mitalk_admin_android.mvi.ChatState
import com.example.mitalk_admin_android.socket.ChatSocket
import com.example.mitalk_admin_android.ui.chat.ChatData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val postFileUseCase: PostFileUseCase
) : ContainerHost<ChatState, ChatSideEffect>, ViewModel() {
    override val container = container<ChatState, ChatSideEffect>(ChatState())
    fun setChatSocket(
        chatSocket: ChatSocket,
    ) = intent {
        reduce { state.copy(chatSocket = chatSocket) }
    }

    fun getAccessToken() = intent {
        viewModelScope.launch {
            getAccessTokenUseCase()
                .onSuccess {
                    reduce { state.copy(accessToken = it) }
                }
        }
    }

    fun postFile(file: File) = intent {
        viewModelScope.launch {
            postFileUseCase(file)
                .onSuccess {
                    postSideEffect(ChatSideEffect.SuccessUpload(it.file))
                }.onFailure {
                }
        }
    }

    fun receiveChat(chat: ChatData) = intent {
        postSideEffect(ChatSideEffect.ReceiveChat(chat))
    }

    fun receiveChatUpdate(chat: ChatData) = intent {
        postSideEffect(ChatSideEffect.ReceiveChatUpdate(chat))
    }

    fun receiveChatDelete(chatId: String) = intent {
        postSideEffect(ChatSideEffect.ReceiveChatDelete(chatId))
    }

    fun successRoom(roomId: String) = intent {
        postSideEffect(ChatSideEffect.SuccessRoom(roomId = roomId))
    }

    fun finishRoom() = intent {
        postSideEffect(ChatSideEffect.FinishRoom)
    }
}