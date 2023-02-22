package com.example.mitalk_admin_android.ui.admin.messagerecord

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mitalk_admin_android.AppNavigationItem
import com.example.mitalk_admin_android.DeepLinkKey
import com.example.mitalk_admin_android.R
import com.example.mitalk_admin_android.ui.admin.header.AdminHeader
import com.example.mitalk_admin_android.ui.admin.header.addFocusCleaner
import com.example.mitalk_admin_android.ui.util.RecordItemType
import com.example.mitalk_admin_android.ui.util.bottomBorder
import com.example.mitalk_admin_android.util.miClickable
import com.example.mitalk_admin_android.util.theme.Light13GM
import com.example.mitalk_admin_android.vm.admin.AdminMessageRecordViewModel

@Composable
fun AdminMessageRecordScreen(
    navController: NavController,
    vm: AdminMessageRecordViewModel = hiltViewModel(),
) {
    val container = vm.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    LaunchedEffect(vm) {
        vm.getMessageRecordList()
    }

    val focusManager = LocalFocusManager.current
    var findOn by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .addFocusCleaner(focusManager) {
                findOn = false
            }
    ) {
        AdminHeader(
            navController = navController,
            title = stringResource(id = R.string.admin_message_record_title),
            hint = stringResource(id = R.string.input_search),
            findOn = findOn,
            findOnPressed = {
                searchText = it
            },
            findOnRequest = {
                findOn = true
            }
        )
        LazyColumn {
            items(state.recordList) {
                if (
                    it.startAt.contains(searchText)
                    || it.counsellorName.contains(searchText)
                    || it.customerName.contains(searchText)
                    || it.type.contains(searchText)
                ) {
                    val type = when (it.type) {
                        RecordItemType.QUESTION.type -> RecordItemType.QUESTION
                        RecordItemType.FEEDBACK.type -> RecordItemType.FEEDBACK
                        RecordItemType.BUG.type -> RecordItemType.BUG
                        RecordItemType.SUGGEST.type -> RecordItemType.SUGGEST
                        RecordItemType.INQUIRY.type -> RecordItemType.INQUIRY
                        RecordItemType.ETC.type -> RecordItemType.ETC
                        else -> RecordItemType.ElSE
                    }
                    MessageRecordItem(
                        date = it.startAt,
                        counsellorName = it.counsellorName,
                        userName = it.customerName,
                        type = type
                    ) {
                        navController.navigate(
                            route = AppNavigationItem.RecordDetail.route
                                    + DeepLinkKey.HEADER_ID + type.titleId
                                    + DeepLinkKey.RECORD_ID + it.id
                                    + DeepLinkKey.Role + "Admin"
                                    + DeepLinkKey.CounsellorName + it.counsellorName
                                    + DeepLinkKey.CustomerName + it.customerName
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun MessageRecordItem(
    date: String,
    //time: String,
    counsellorName: String,
    userName: String,
    type: RecordItemType,
    onPressed: () -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 5.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier = Modifier
                .bottomBorder(
                    modifier = Modifier
                        .fillMaxWidth(),
                    color = Color(0xFFD7D7D7),
                    strokeWidth = 1.dp
                )
        ) {
            Light13GM(
                text = buildAnnotatedString {
                    append(date)
                    append(" ")
                    append(counsellorName)
                    append(" ")
                    append(userName)
                    append(" ")
                    append(stringResource(id = type.titleId))
                }.toString()
            )
            Light13GM(
                text = stringResource(id = R.string.open_detail),
                color = Color(0xFF4B1C1C),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(align = Alignment.End)
                    .miClickable(
                        rippleEnabled = false
                    ) {
                        onPressed()
                    }
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ShowMessageRecordScreen() {
    val controller = rememberNavController()

    AdminMessageRecordScreen(navController = controller)
}