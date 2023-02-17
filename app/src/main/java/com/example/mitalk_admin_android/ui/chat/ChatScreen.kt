package com.example.mitalk_admin_android.ui.chat

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mitalk_admin_android.R
import com.example.mitalk_admin_android.mvi.ChatSideEffect
import com.example.mitalk_admin_android.util.miClickable
import com.example.mitalk_admin_android.util.observeWithLifecycle
import com.example.mitalk_admin_android.util.theme.MiTalkIcon
import com.example.mitalk_admin_android.vm.ChatViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.time.LocalTime

@Stable
private val CounselorChat =
    RoundedCornerShape(topStart = 0.dp, topEnd = 5.dp, bottomEnd = 5.dp, bottomStart = 5.dp)

@Stable
private val ClientChat =
    RoundedCornerShape(topStart = 5.dp, topEnd = 5.dp, bottomEnd = 0.dp, bottomStart = 5.dp)

data class ChatData(
    val text: String,
    val isMe: Boolean,
    val time: String,
)

@OptIn(InternalCoroutinesApi::class)
@Composable
fun ChatScreen(
    navController: NavController,
    roomId: String,
    vm: ChatViewModel = hiltViewModel(),
) {
    val container = vm.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    var chatList = remember { mutableStateListOf<ChatData>() }
    var chatListState = rememberLazyListState()

    sideEffect.observeWithLifecycle {
        when (it) {
            is ChatSideEffect.ReceiveChat -> {
                chatList.add(
                    ChatData(
                        text = it.chat,
                        isMe = false,
                        time = LocalTime.now().toString()
                    )
                )
                MainScope().launch {
                    chatListState.scrollToItem(chatList.size - 1)
                }
            }
        }
    }

    Column {
        Box(modifier = Modifier.weight(1f)) {
            ChatList(chatList = chatList, chatListState = chatListState)
        }
        ChatInput(sendAction = {
            state.chatSocket.send(roomId, it)
            chatList.add(ChatData(text = it, isMe = true, time = LocalTime.now().toString()))
            MainScope().launch {
                chatListState.scrollToItem(chatList.size - 1)
            }
        })
        Spacer(modifier = Modifier.height(18.dp))
    }
}

@Composable
fun ChatList(chatList: List<ChatData>, chatListState: LazyListState = rememberLazyListState()) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        state = chatListState
    ) {
        items(1) {
            Spacer(modifier = Modifier.height(20.dp))
        }
        itemsIndexed(chatList) { _, item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(start = 20.dp, end = 10.dp, top = 10.dp),
                horizontalArrangement = if (item.isMe) Arrangement.End else Arrangement.Start
            ) {
                if (item.isMe) {
                    ClientChat(item = item)
                } else {
                    CounselorChat(item = item)
                }
            }
        }
        items(1) {
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun ChatInput(
    sendAction: (String) -> Unit,
) {
    var isExpand by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }
    var targetValue by remember { mutableStateOf(0F) }
    val rotateValue: Float by animateFloatAsState(
        targetValue = targetValue,
        tween(300)
    )
    Row(
        modifier = Modifier
            .height(40.dp)
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.width(7.dp))
        IconButton(icon = MiTalkIcon.Plus, modifier = Modifier.rotate(rotateValue), onClick = {
            isExpand = !isExpand
            targetValue = if (isExpand) {
                45F
            } else {
                0F
            }
        })
        if (isExpand) {
            Spacer(modifier = Modifier.width(5.dp))
            IconButton(icon = MiTalkIcon.Picture, onClick = {

            })
            Spacer(modifier = Modifier.width(5.dp))
            IconButton(icon = MiTalkIcon.Video, onClick = {

            })
            Spacer(modifier = Modifier.width(5.dp))
            IconButton(icon = MiTalkIcon.Document, onClick = {

            })
        }
        Spacer(modifier = Modifier.width(5.dp))
        ChatEditText(value = text, modifier = Modifier.weight(1f), onValueChange = {
            text = it
        })
        IconButton(icon = MiTalkIcon.Send, onClick = {
            if (!text.isNullOrBlank()) {
                sendAction(text)
            }
            text = ""
        })
    }
}

@Composable
fun ChatEditText(value: String, onValueChange: (String) -> Unit, modifier: Modifier) {
    Row(
        modifier = modifier
            .fillMaxHeight()
            .background(color = Color.White, shape = RoundedCornerShape(13.dp))
            .border(width = 1.dp, color = Color(0xD2D2D2), shape = RoundedCornerShape(13.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            value = value,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 5.dp),
            onValueChange = { onValueChange(it) },
            decorationBox = @Composable {
                Box(
                    contentAlignment = Alignment.CenterStart,
                ) {
                    it()
                }
            }
        )
    }
}

@Composable
fun CounselorChat(
    item: ChatData,
) {
    Row(
        verticalAlignment = Alignment.Bottom
    ) {
        Image(
            painter = painterResource(id = MiTalkIcon.Client.drawableId),
            contentDescription = MiTalkIcon.Client.contentDescription,
            modifier = Modifier
                .size(35.dp)
                .align(Alignment.Top),
        )
        Spacer(modifier = Modifier.width(3.dp))
        Column {
            Text(text = stringResource(id = R.string.main))
            Text(
                text = item.text,
                color = Color.White,
                modifier = Modifier
                    .background(
                        color = Color.Blue,
                        shape = CounselorChat
                    )
                    .widthIn(min = 0.dp, max = 180.dp)
                    .padding(horizontal = 7.dp, vertical = 5.dp)
            )
        }
        Spacer(modifier = Modifier.width(3.dp))
        Text(text = item.time)
    }
}

@Composable
fun ClientChat(
    item: ChatData,
) {
    Row(
        verticalAlignment = Alignment.Bottom
    ) {
        Text(text = item.time)
        Spacer(modifier = Modifier.width(3.dp))
        Text(
            text = item.text,
            modifier = Modifier
                .background(
                    color = Color.White,
                    shape = ClientChat
                )
                .widthIn(min = 0.dp, max = 200.dp)
                .padding(horizontal = 7.dp, vertical = 5.dp)
        )
    }
}

@Composable
fun IconButton(
    icon: MiTalkIcon,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = Modifier
        .width(20.dp)
        .miClickable(rippleEnabled = false) {
            onClick()
        }) {
        Icon(
            painter = painterResource(id = icon.drawableId),
            contentDescription = icon.contentDescription,
            tint = Color.Blue,
            modifier = modifier
                .fillMaxHeight()
        )
    }
}