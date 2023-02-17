package com.example.mitalk_admin_android.ui.admin

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mitalk_admin_android.ui.util.bottomBorder
import com.example.mitalk_admin_android.util.theme.MiTalkColor
import com.example.mitalk_admin_android.util.theme.MiTalkIcon
import com.example.mitalk_admin_android.util.theme.Regular12NO

@Composable
fun AdminIssuedScreen(
    navController: NavController
) {
    AdminIssuedItem(
        name = "이름",
        key = "f13981aa-adca-11ed-afa1-0242ac120002",
        state = "on"
    ) {

    }
}

@Composable
fun AdminIssuedItem(
    name: String,
    key: String,
    state: String,
    deletePressed: (String) -> Unit
) {
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