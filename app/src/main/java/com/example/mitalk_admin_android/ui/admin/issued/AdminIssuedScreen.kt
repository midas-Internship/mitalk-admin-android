package com.example.mitalk_admin_android.ui.admin.issued

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mitalk_admin_android.ui.admin.header.AdminHeader
import com.example.mitalk_admin_android.ui.util.bottomBorder
import com.example.mitalk_admin_android.R
import com.example.mitalk_admin_android.mvi.admin.AdminIssuedSideEffect
import com.example.mitalk_admin_android.ui.admin.dialog.AdminDialog
import com.example.mitalk_admin_android.ui.admin.header.addFocusCleaner
import com.example.mitalk_admin_android.ui.util.MiIconButton
import com.example.mitalk_admin_android.util.observeWithLifecycle
import com.example.mitalk_admin_android.util.rememberToast
import com.example.mitalk_admin_android.util.theme.*
import com.example.mitalk_admin_android.vm.admin.AdminIssuedViewModel
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(InternalCoroutinesApi::class)
@Composable
fun AdminIssuedScreen(
    navController: NavController,
    vm: AdminIssuedViewModel = hiltViewModel()
) {
    val toast = rememberToast()
    val failToast = stringResource(id = R.string.delete_fail)

    val container = vm.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    var dialogVisible by remember { mutableStateOf(false) }
    var deleteId by remember { mutableStateOf("") }
    var deleteName by remember { mutableStateOf("") }

    sideEffect.observeWithLifecycle {
        when (it) {
            AdminIssuedSideEffect.StateRefresh -> {
                vm.getCounsellorList()
            }
            AdminIssuedSideEffect.DeleteSuccess -> {
                vm.getCounsellorList()
                dialogVisible = false
            }
            AdminIssuedSideEffect.DeleteFail -> {
                toast(message = failToast)
            }
        }
    }

    LaunchedEffect(vm) {
        vm.getCounsellorList()
    }

    val focusManager = LocalFocusManager.current
    var findOn by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }

    var addCounsellorName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .addFocusCleaner(focusManager) {
                findOn = false
            }
    ) {
        AdminHeader(
            navController = navController,
            title = stringResource(id = R.string.admin_account_issued_title),
            hint = stringResource(id = R.string.input_search_name),
            findOn = findOn,
            findOnPressed = {
                searchText = it
            },
            findOnRequest = {
                findOn = true
            }
        )
        if (dialogVisible) {
            AdminDialog(
                onCheck = {
                    vm.deleteCounsellor(deleteId)
                },
                onCancel = {
                    dialogVisible = false
                },
                title = stringResource(id = R.string.real_delete),
                name = deleteName,
                id = deleteId,
                hint = stringResource(id = R.string.can_not_counsel)
            ) {
                dialogVisible = false
            }
        }

        Column(
            modifier = Modifier
                .padding(horizontal = 12.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .bottomBorder(
                        modifier = Modifier.fillMaxWidth(),
                        strokeWidth = 1.dp,
                        color = Color(0x80848484)
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                MiIconButton(
                    onClick = {
                        if (addCounsellorName.isNotEmpty()) {
                            vm.addCounsellor(name = addCounsellorName)
                            addCounsellorName = ""
                        }
                    }
                ) {
                    Image(
                        painter = painterResource(id = MiTalkIcon.Add_Green_Icon.drawableId),
                        contentDescription = MiTalkIcon.Add_Green_Icon.contentDescription,
                        modifier = Modifier.size(11.dp)
                    )
                }
                BasicTextField(
                    value = addCounsellorName,
                    onValueChange = { addCounsellorName = it },
                    textStyle = MiTalkAdminTypography.regular14NO,
                    decorationBox = @Composable {
                        it()
                        if (addCounsellorName.isEmpty()) {
                            Regular14NO(
                                text = stringResource(id = R.string.input_name),
                                color = Color(0xFF959595)
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            LazyColumn {
                items(state.counsellorList) { item ->
                    if (item.name.contains(searchText)) {
                        AdminIssuedItem(
                            name = item.name,
                            key = item.counsellorId,
                            state = item.status,
                            deletePressed = { name, id ->
                                deleteName = name
                                deleteId = id
                                dialogVisible = true
                            }
                        )
                    }
                }
            }
        }
    }

}

@Composable
fun AdminIssuedItem(
    name: String,
    key: String,
    state: String,
    deletePressed: (String, String) -> Unit
) {
    val context = LocalContext.current

    val keyColor = when (state) {
        "ONLINE" -> Color(0xFF56A470)
        "OFFLINE" -> Color(0xFFD43333)
        "COUNSELLING" -> Color(0xFF6F72B0)
        else -> MiTalkColor.Black
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .bottomBorder(
                modifier = Modifier.fillMaxWidth(),
                strokeWidth = 1.dp,
                color = Color(0x80848484)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        MiIconButton(
            onClick = {
                deletePressed(name, key)
            }
        ) {
            Icon(
                painter = painterResource(id = MiTalkIcon.Cancel_Icon.drawableId),
                contentDescription = MiTalkIcon.Cancel_Icon.contentDescription,
            )
        }
        Regular12NO(
            text = buildAnnotatedString {
                append(name)
                append(":")
            }.toString(),
            color = Color(0xFF505050)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Regular12NO(
            text = key,
            color = keyColor
        )
        MiIconButton(
            onClick = {
                (context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager).setPrimaryClip(
                    ClipData.newPlainText("key:", key)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(align = Alignment.End)
        ) {
            Icon(
                painter = painterResource(id = MiTalkIcon.Copy_Icon.drawableId),
                contentDescription = MiTalkIcon.Copy_Icon.contentDescription
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ShowAdminIssuedScreen() {
    val navController = rememberNavController()
    AdminIssuedScreen(navController = navController)
}