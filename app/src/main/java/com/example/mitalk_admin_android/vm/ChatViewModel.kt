package com.example.mitalk_admin_android.vm

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.auth.GetAccessTokenUseCase
import com.example.domain.usecase.file.PostFileUseCase
import com.example.mitalk_admin_android.mvi.ChatSideEffect
import com.example.mitalk_admin_android.mvi.ChatState
import com.example.mitalk_admin_android.socket.ChatSocket
import com.example.mitalk_admin_android.socket.toDeleteChatData
import com.example.mitalk_admin_android.ui.counsellor.chat.ChatData
import com.example.mitalk_admin_android.util.FileNotAllowedException
import com.example.mitalk_admin_android.util.FileOverException
import com.example.mitalk_admin_android.util.FileSizeException
import com.example.mitalk_admin_android.util.toFile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val postFileUseCase: PostFileUseCase,
) : ContainerHost<ChatState, ChatSideEffect>, ViewModel() {
    override val container = container<ChatState, ChatSideEffect>(ChatState())
    fun setChatSocket(
        chatSocket: ChatSocket,
    ) = intent {
        reduce { state.copy(chatSocket = chatSocket) }
    }

    fun startSocket() = intent {
        state.chatSocket.startSocket(accessToken = state.accessToken)
    }

    fun closeSocket() = intent {
        state.chatSocket.close()
    }

    fun changeIsOccupied() = intent {
        reduce { state.copy(isOccupied = !state.isOccupied) }
    }

    fun getAccessToken() = intent {
        viewModelScope.launch {
            getAccessTokenUseCase()
                .onSuccess {
                    reduce { state.copy(accessToken = it) }
                }
        }
    }

    fun postFile(uri: Uri, context: Context, approve: Boolean = false) = intent {
        kotlin.runCatching {
            uri.toFile(context, approve)
        }.onSuccess { file ->
            reduce { state.copy(uploadList = state.uploadList.plus(uri)) }
            viewModelScope.launch {
                postFileUseCase(file)
                    .onSuccess {
                        reduce { state.copy(uploadList = state.uploadList.filter { uploadUri -> uri != uploadUri }) }
                        postSideEffect(ChatSideEffect.SuccessUpload(it.file))
                    }.onFailure {
                    }
            }
        }.onFailure {
            when (it) {
                is FileSizeException -> {
                    postSideEffect(ChatSideEffect.FileSizeException(uri = uri))
                }
                is FileOverException -> {
                    postSideEffect(ChatSideEffect.FileOverException)
                }
                is FileNotAllowedException -> {
                    postSideEffect(ChatSideEffect.FileNotAllowedException)
                }
            }
        }
    }

    fun receiveChat(chat: ChatData) = intent {
        reduce { state.copy(chatList = state.chatList.plus(chat)) }
        postSideEffect(ChatSideEffect.ReceiveChat(chat, state.chatList.size))
    }

    fun receiveChatUpdate(chat: ChatData) = intent {
        postSideEffect(ChatSideEffect.ReceiveChatUpdate(chat))
    }

    fun receiveChatDelete(chatId: String) = intent {
        postSideEffect(ChatSideEffect.ReceiveChatDelete(chatId))
    }

    fun editChatList(chatData: ChatData) = intent {
        reduce {
            state.copy(chatList = state.chatList.map {
                if (it.id == chatData.id) chatData else it
            })
        }
    }

    fun deleteChatList(chatId: String, deleteMsg: String) = intent {
        reduce {
            state.copy(chatList = state.chatList.map {
                if (it.id == chatId) it.toDeleteChatData(
                    deleteMsg
                ) else it
            })
        }
    }

    fun successRoom(name: String) = intent {
        reduce { state.copy(customerName = name) }
        postSideEffect(ChatSideEffect.SuccessRoom(name = name))
    }

    fun finishRoom() = intent {
        state.chatSocket.close()
        reduce { state.copy(chatList = listOf(), uploadList = listOf()) }
        postSideEffect(ChatSideEffect.FinishRoom)
    }
}