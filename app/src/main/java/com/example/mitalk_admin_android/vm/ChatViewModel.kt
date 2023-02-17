package com.example.mitalk_admin_android.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.auth.GetAccessTokenUseCase
import com.example.mitalk_admin_android.mvi.ChatSideEffect
import com.example.mitalk_admin_android.mvi.ChatState
import com.example.mitalk_admin_android.socket.ChatSocket
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
    private val getAccessTokenUseCase: GetAccessTokenUseCase
) : ContainerHost<ChatState, ChatSideEffect>, ViewModel() {
    override val container = container<ChatState, ChatSideEffect>(ChatState())
    fun setChatSocket(
        chatSocket: ChatSocket
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
}