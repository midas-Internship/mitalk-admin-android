package com.example.mitalk_admin_android.ui.admin.usercare

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.*
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
import com.example.mitalk_admin_android.R
import com.example.mitalk_admin_android.ui.admin.header.AdminHeader
import com.example.mitalk_admin_android.ui.admin.header.addFocusCleaner
import com.example.mitalk_admin_android.util.theme.Medium12NO
import com.example.mitalk_admin_android.util.theme.MiTalkColor
import com.example.mitalk_admin_android.vm.admin.AdminUserCareViewModel

@Composable
fun AdminUserCareScreen(
    navController: NavController,
    vm: AdminUserCareViewModel = hiltViewModel()
) {
    val container = vm.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    val focusManager = LocalFocusManager.current
    var findOn by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }

    LaunchedEffect(vm) {
        vm.getUserList()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .addFocusCleaner(focusManager) {
                findOn = false
            }
    ) {
        AdminHeader(
            navController = navController,
            title = stringResource(id = R.string.admin_user_care_title),
            hint = stringResource(id = R.string.input_search),
            findOn = findOn,
            findOnPressed = {
                searchText = it
            },
            findOnRequest = {
                findOn = true
            }
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            itemsIndexed(state.userList) { index, item ->
                if(
                    item.id.contains(searchText)
                    || item.name.contains(searchText)
                    || item.email.contains(searchText)
                ) {
                    AdminUserCareItem(
                        id = item.id,
                        name = item.name,
                        email = item.email,
                        session = item.session,
                        index = index
                    )
                }
            }
        }
    }

}

@Composable
private fun AdminUserCareItem(
    id: String,
    name: String,
    email: String,
    session: String?,
    index: Int,
) {
    AdminUserItemContent(
        title = "id",
        content = id,
        index = index
    )
    AdminUserItemContent(
        title = "name",
        content = name,
        index = index
    )
    AdminUserItemContent(
        title = "email",
        content = email,
        index = index
    )
    AdminUserItemContent(
        title = "session",
        content = session,
        index = index
    )
}

@Composable
private fun AdminUserItemContent(
    title: String,
    content: String?,
    index: Int
) {
    val textColor =
        if (index % 2 == 0) MiTalkColor.Black
        else Color(0xFF6D6D6D)

    Row {
        Spacer(modifier = Modifier.width(16.dp))

        Medium12NO(
            text = title,
            color = textColor,
            modifier = Modifier.width(45.dp)
        )
        Medium12NO(
            text = buildAnnotatedString {
                append(":")
                content?.let { append(it) }
            }.toString(),
            color = textColor,
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ShowAdminUserCareScreen() {
    val navController = rememberNavController()
    AdminUserCareScreen(navController = navController)
}
