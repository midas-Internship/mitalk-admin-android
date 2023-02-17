package com.example.mitalk_admin_android.ui.admin

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
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
import com.example.mitalk_admin_android.util.theme.*
import com.example.mitalk_admin_android.vm.admin.AdminIssuedViewModel

@Composable
fun AdminIssuedScreen(
    navController: NavController,
    vm: AdminIssuedViewModel = hiltViewModel()
) {

    val container = vm.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    var addCounsellorName by remember { mutableStateOf("") }

    Column {
        AdminHeader(
            navController = navController,
            title = stringResource(id = R.string.admin_account_issued_title)
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
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
                IconButton(
                    onClick = {  }
                ) {
                    Icon(
                        painter = painterResource(id = MiTalkIcon.Add_Green_Icon.drawableId),
                        contentDescription = MiTalkIcon.Add_Green_Icon.contentDescription,
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
                    AdminIssuedItem(
                        name = item.name,
                        key = item.counsellorId,
                        state = item.status,
                        deletePressed = { vm.deleteCounsellor(item.counsellorId) }
                    )
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
    deletePressed: (String) -> Unit
) {
    val context = LocalContext.current

    val keyColor = when (state) {
        "on" -> Color(0xFF56A470)
        "off" -> Color(0xFFD43333)
        "counselling" -> Color(0xFF6F72B0)
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
        IconButton(
            onClick = {
                deletePressed(key)
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
        IconButton(
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