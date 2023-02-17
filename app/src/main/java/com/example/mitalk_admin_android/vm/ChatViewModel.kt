package com.example.mitalk_admin_android.vm

import androidx.lifecycle.ViewModel
import com.example.mitalk_admin_android.mvi.ChatState
import com.example.mitalk_admin_android.socket.ChatSocket
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(

) : ContainerHost<ChatState, Unit>, ViewModel() {
    override val container = container<ChatState, Unit>(ChatState())
    fun setChatSocket(
        chatSocket: ChatSocket
    ) = intent {
        reduce { state.copy(chatSocket = chatSocket) }
    }
}