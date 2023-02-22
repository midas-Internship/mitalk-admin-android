package com.example.mitalk_admin_android.ui.record

import android.widget.Toast
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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mitalk_admin_android.R
import com.example.mitalk_admin_android.mvi.RecordDetailSideEffect
import com.example.mitalk_admin_android.mvi.RecordDetailState
import com.example.mitalk_admin_android.ui.counsellor.chat.ChatItem
import com.example.mitalk_admin_android.ui.util.ClientChatShape
import com.example.mitalk_admin_android.ui.util.CounselorChatShape
import com.example.mitalk_admin_android.ui.util.MiHeader
import com.example.mitalk_admin_android.util.miClickable
import com.example.mitalk_admin_android.util.observeWithLifecycle
import com.example.mitalk_admin_android.util.theme.*
import com.example.mitalk_admin_android.util.toChatTime
import com.example.mitalk_admin_android.vm.record.RecordDetailViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@OptIn(InternalCoroutinesApi::class)
@Composable
fun RecordDetailScreen(
    navController: NavController,
    headerId: Int,
    recordId: String,
    role: String,
    counsellorName: String,
    customerName: String,
    vm: RecordDetailViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val chatListState = rememberLazyListState()
    var text by remember { mutableStateOf("") }
    var findText by remember { mutableStateOf("") }
    var messageId by remember { mutableStateOf("") }
    var logDialogVisible by remember { mutableStateOf(false) }
    val noSearchMsg = stringResource(id = R.string.no_search_result)
    var chatLog by remember { mutableStateOf(listOf<RecordDetailState.MessageRecordData.MessageData>()) }

    val container = vm.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    LaunchedEffect(Unit) {
        vm.getRecordDetail(recordId = recordId)
    }

    sideEffect.observeWithLifecycle {
        when (it) {
            is RecordDetailSideEffect.ChangeCurrentFindPosition -> {
                MainScope().launch {
                    chatListState.scrollToItem(it.list[it.scrollPosition])
                }
            }
        }
    }

    Column {
        MiHeader(
            backPressed = {
                navController.popBackStack()
            },
            text = stringResource(id = headerId),
        )
        Spacer(modifier = Modifier.height(10.dp))
        FindInput(
            text = text,
            isFind = state.totalFindResultList.isNotEmpty(),
            currentResult = state.currentFindPosition + 1,
            totalResult = state.totalFindResultList,
            onTextChange = { text = it },
            onFindAction = {
                findText = text
                val list = state.messageRecords.mapIndexed { index, data ->
                    if (!data.isDeleted && data.dataMap.last().message.contains(text)) index else null
                }.filterNotNull().toMutableList()
                vm.setTotalFindResultList(list)
                if (list.isEmpty()) {
                    Toast.makeText(context, noSearchMsg, Toast.LENGTH_SHORT).show()
                } else {
                    focusManager.clearFocus()
                }
            }, onCancelAction = {
                vm.clearFindResult()
                text = ""
                findText = ""
            }, upFindAction = {
                if (state.currentFindPosition > 0) {
                    vm.minusCurrentFindPosition()
                }
            }, downFindAction = {
                if (state.currentFindPosition < state.totalFindResultList.size - 1) {
                    vm.plusCurrentFindPosition()
                }
            })
        Box(modifier = Modifier.weight(1f)) {
            ChatList(
                counsellorName = counsellorName,
                customerName = customerName,
                chatList = state.messageRecords,
                chatListState = chatListState,
                findText = findText,
                role = role
            ) { id ->
                if (role == "Admin") {
                    logDialogVisible = true
                    chatLog = state.messageRecords.first { it.messageId == id }.dataMap
                }
            }
        }
        RecordLogDialog(
            visible = logDialogVisible,
            onDismissRequest = { logDialogVisible = false },
            itemList = chatLog
        )
    }
}

@Composable
fun FindInput(
    text: String,
    isFind: Boolean,
    currentResult: Int,
    totalResult: List<Int>,
    onTextChange: (String) -> Unit,
    onFindAction: () -> Unit,
    onCancelAction: () -> Unit,
    downFindAction: () -> Unit,
    upFindAction: () -> Unit,
) {
    Row(
        modifier = Modifier
            .height(30.dp)
            .padding(horizontal = 20.dp)
            .background(color = MiTalkColor.White, shape = RoundedCornerShape(5.dp))
            .border(width = 1.dp, color = MiTalkColor.MainBlue, shape = RoundedCornerShape(5.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            value = "$text${if (isFind) " $currentResult/${totalResult.size}" else ""}",
            modifier = Modifier
                .weight(1F)
                .padding(horizontal = 15.dp, vertical = 5.dp),
            enabled = !isFind,
            onValueChange = { onTextChange(it) },
            textStyle = MiTalkAdminTypography.regular14NO,
            singleLine = true,
            decorationBox = @Composable {
                Box(
                    contentAlignment = Alignment.CenterStart,
                ) {
                    it()
                    if (text.isEmpty()) {
                        Regular12NO(
                            text = stringResource(id = R.string.find_comment_hint),
                            color = Color(0xFFACABED)
                        )
                    }
                }
            }
        )
        if (!isFind) {
            Icon(
                painter = painterResource(id = MiTalkIcon.Search.drawableId),
                contentDescription = MiTalkIcon.Search.contentDescription,
                modifier = Modifier
                    .width(15.dp)
                    .miClickable(rippleEnabled = false) {
                        if (!text.isNullOrBlank()) {
                            onFindAction()
                        }
                    }
            )
        } else {
            Icon(
                painter = painterResource(id = MiTalkIcon.Up.drawableId),
                contentDescription = MiTalkIcon.Up.contentDescription,
                modifier = Modifier
                    .width(15.dp)
                    .miClickable(rippleEnabled = false) {
                        upFindAction()
                    }
            )
            Icon(
                painter = painterResource(id = MiTalkIcon.Down.drawableId),
                contentDescription = MiTalkIcon.Down.contentDescription,
                modifier = Modifier
                    .width(15.dp)
                    .miClickable(rippleEnabled = false) {
                        downFindAction()
                    }
            )
            Spacer(modifier = Modifier.width(5.dp))
            Icon(
                painter = painterResource(id = MiTalkIcon.Cancel.drawableId),
                contentDescription = MiTalkIcon.Cancel.contentDescription,
                modifier = Modifier
                    .width(15.dp)
                    .miClickable(rippleEnabled = false) {
                        onCancelAction()
                    }
            )
        }
        Spacer(modifier = Modifier.width(15.dp))
    }
}

@Composable
fun ChatList(
    counsellorName: String,
    customerName: String,
    chatList: List<RecordDetailState.MessageRecordData>,
    chatListState: LazyListState = rememberLazyListState(),
    findText: String,
    role: String,
    onClick: (String) -> Unit
) {
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
                horizontalArrangement = if (item.sender == "COUNSELLOR") Arrangement.End else Arrangement.Start
            ) {
                if (item.sender == "COUNSELLOR") {
                    CounselorChat(
                        name = counsellorName,
                        item = item,
                        findText = findText,
                        onClick = onClick,
                        role = role
                    )
                } else {
                    ClientChat(
                        name = customerName,
                        item = item,
                        findText = findText,
                        onClick = onClick
                    )
                }
            }
        }
        items(1) {
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun ClientChat(
    name: String,
    item: RecordDetailState.MessageRecordData,
    findText: String,
    onClick: (String) -> Unit
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
            Regular10NO(text = "$name ${stringResource(id = R.string.client)}")
            if (item.isDeleted) Bold12NO(text = stringResource(id = R.string.delete_message)) else {
                ChatItem(
                    item = item.dataMap.last().message,
                    isMe = item.sender == "COUNSELLOR",
                    modifier = Modifier
                        .miClickable(rippleEnabled = false) { onClick(item.messageId) }
                        .background(
                            color = MiTalkColor.MainBlue,
                            shape = ClientChatShape
                        )
                        .widthIn(min = 0.dp, max = 180.dp)
                        .padding(horizontal = 7.dp, vertical = 5.dp),
                    findText = findText
                )
            }
        }
        Spacer(modifier = Modifier.width(3.dp))
        Regular10NO(text = item.dataMap.last().time.toChatTime())
    }
}

@Composable
fun CounselorChat(
    name: String,
    item: RecordDetailState.MessageRecordData,
    findText: String,
    onClick: (String) -> Unit,
    role: String
) {
    Box {
        Row(
            verticalAlignment = Alignment.Bottom
        ) {
            Regular10NO(text = item.dataMap.last().time.toChatTime())
            Spacer(modifier = Modifier.width(3.dp))
            Column(
                horizontalAlignment = Alignment.End
            ) {
                if (role == "Admin") {
                    Regular10NO(text = "$name ${stringResource(id = R.string.counsellor)}")
                }
                Box(
                    modifier = Modifier
                        .miClickable(rippleEnabled = false) { onClick(item.messageId) }
                        .background(
                            color = MiTalkColor.White,
                            shape = CounselorChatShape
                        )
                        .widthIn(min = 0.dp, max = 200.dp)
                        .padding(horizontal = 7.dp, vertical = 5.dp)
                ) {

                    if (item.isDeleted) Bold12NO(text = stringResource(id = R.string.delete_message)) else {
                        ChatItem(item = item.dataMap.last().message, findText = findText)
                    }
                }
            }
            if (role == "Admin") {
                Image(
                    painter = painterResource(id = MiTalkIcon.Counselor.drawableId),
                    contentDescription = MiTalkIcon.Counselor.contentDescription,
                    modifier = Modifier
                        .size(35.dp)
                        .align(Alignment.Top),
                )
            }
        }
    }
}

@Composable
@Preview
fun showRecordDetailScreen() {
    val navController = rememberNavController()
    RecordDetailScreen(navController = navController, 0, "헤더", "", "", "")
}