package com.example.mitalk_admin_android.ui.counsellor.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mitalk_admin_android.AppNavigationItem
import com.example.mitalk_admin_android.DeepLinkKey
import com.example.mitalk_admin_android.R
import com.example.mitalk_admin_android.mvi.ChatSideEffect
import com.example.mitalk_admin_android.mvi.admin.AdminMainSideEffect
import com.example.mitalk_admin_android.socket.ChatSocket
import com.example.mitalk_admin_android.ui.counsellor.dialog.ExitChatDialog
import com.example.mitalk_admin_android.ui.util.ContentShape
import com.example.mitalk_admin_android.ui.util.MiHeader
import com.example.mitalk_admin_android.util.miClickable
import com.example.mitalk_admin_android.util.observeWithLifecycle
import com.example.mitalk_admin_android.util.theme.*
import com.example.mitalk_admin_android.vm.ChatViewModel
import com.example.mitalk_admin_android.vm.LogoutViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlin.math.log

@OptIn(InternalCoroutinesApi::class)
@Composable
fun CounsellorMainScreen(
    navController: NavController,
    vm: ChatViewModel = hiltViewModel(),
    logoutViewModel: LogoutViewModel = hiltViewModel(),
) {
    var counselOnOFF by remember { mutableStateOf(false) }
    val counselorImage =
        if (counselOnOFF) painterResource(id = MiTalkIcon.Counsellor_On_Img.drawableId)
        else painterResource(id = MiTalkIcon.Counsellor_Off_Img.drawableId)
    val counselorStateText =
        if (counselOnOFF) stringResource(id = R.string.can_counsel)
        else stringResource(id = R.string.can_not_counsel)

    val container = vm.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    var exitDialogVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        vm.getAccessToken()
        vm.setChatSocket(ChatSocket(successAction = {
            vm.successRoom(it)
        }, finishAction = {
            vm.finishRoom()
        }, receiveAction = {
            vm.receiveChat(it)
        }, receiveActionUpdate = {
            vm.receiveChatUpdate(it)
        }, receiveActionDelete = {
            vm.receiveChatDelete(it)
        }))
    }

    sideEffect.observeWithLifecycle {
        when (it) {
            is ChatSideEffect.SuccessRoom -> {
                navController.navigate(AppNavigationItem.Chat.route)
            }
        }
    }

    val logoutContainer = logoutViewModel.container
    val logoutSideEffect = logoutContainer.sideEffectFlow

    logoutSideEffect.observeWithLifecycle {
        when (it) {
            AdminMainSideEffect.LogoutSuccess -> {
                exitDialogVisible = false
                navController.navigate(AppNavigationItem.Login.route) {
                    popUpTo(0)
                }
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        MiHeader(
            backBtn = false,
            content = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(id = MiTalkIcon.Logo.drawableId),
                        contentDescription = MiTalkIcon.Logo.contentDescription,
                        modifier = Modifier.size(36.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Bold20NO(
                        text = "마이톡-상담원",
                        color = Color(0xFFD44CD7)
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(28.dp))

        Image(
            painter = counselorImage,
            contentDescription = "counselor image",
            modifier = Modifier
                .size(208.dp)
                .miClickable(
                    rippleEnabled = false
                ) {
                    counselOnOFF = !counselOnOFF
                    if (counselOnOFF) {
                        state.chatSocket.startSocket(state.accessToken)
                    } else {
                        state.chatSocket.close()
                    }
                }
        )

        Spacer(modifier = Modifier.height(12.dp))

        Regular14NO(text = counselorStateText)

        Spacer(modifier = Modifier.height(40.dp))

        ExitChatDialog(
            visible = exitDialogVisible,
            title = stringResource(id = R.string.logout),
            content = stringResource(id = R.string.logout_real),
            onDismissRequest = { exitDialogVisible = false }
        ) {
            logoutViewModel.logout()
        }

        MainContent(
            text = stringResource(id = R.string.open_record),
            icon = painterResource(id = MiTalkIcon.Counsellor_Open_Record_Img.drawableId),
            backgroundColor = Color(0xFFBA7D64)
        ) {
            navController.navigate(AppNavigationItem.Record.route)
        }

        Spacer(modifier = Modifier.height(12.dp))

        MainContent(
            text = stringResource(id = R.string.setting),
            backgroundColor = Color(0xFF646464),
            icon = painterResource(id = MiTalkIcon.Setting_Img.drawableId)
        ) {
            navController.navigate(
                route = AppNavigationItem.Setting.route
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        MainContent(
            text = stringResource(id = R.string.logout),
            backgroundColor = Color(0xFF58EBD0),
            icon = painterResource(id = MiTalkIcon.Logout_Img.drawableId)
        ) {
            exitDialogVisible = true
        }
    }
}

@Composable
private fun MainContent(
    text: String,
    backgroundColor: Color,
    icon: Painter,
    onPressed: () -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .height(80.dp)
            .background(
                color = backgroundColor,
                shape = ContentShape,
            )
            .clip(shape = ContentShape)
            .miClickable { onPressed() }
    ) {

        Spacer(modifier = Modifier.width(17.dp))

        Bold26NO(
            text = text,
            color = MiTalkColor.White,
            modifier = Modifier
                .fillMaxHeight()
                .wrapContentHeight(align = Alignment.CenterVertically)
        )

        Image(
            painter = icon,
            contentDescription = "main content icon",
            modifier = Modifier
                .padding(end = 13.dp)
                .fillMaxSize()
                .wrapContentWidth(align = Alignment.End)
                .wrapContentHeight(align = Alignment.CenterVertically)
                .size(60.dp)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ShowCounsellorMainScreen() {
    val navController = rememberNavController()
    CounsellorMainScreen(navController = navController)
}
