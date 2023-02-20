package com.example.mitalk_admin_android.ui.admin.main

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import com.example.mitalk_admin_android.R
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mitalk_admin_android.AppNavigationItem
import com.example.mitalk_admin_android.util.miClickable
import com.example.mitalk_admin_android.util.theme.*

@Composable
fun AdminMainScreen(
    navController: NavController,
    key: String,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MiTalkColor.Gray),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentAlignment = Alignment.Center
        ) {
            Regular25NO(text = stringResource(id = R.string.admin_option))
        }
        Column(
            verticalArrangement = Arrangement.Bottom
        ) {
            AdminContent(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 10.dp)
            ) {
                Column{
                    Light20NO(text = stringResource(id = R.string.admin))
                    Spacer(modifier = Modifier.height(3.dp))
                    Regular10NO(
                        text = buildAnnotatedString {
                            append(stringResource(id = R.string.admin_key))
                            append(" • ")
                            append(key)
                        }.toString(),
                        color = Color(0xFF888888)
                    )
                }

                Image(
                    painter = painterResource(id = MiTalkIcon.Profile.drawableId),
                    contentDescription = MiTalkIcon.Profile.contentDescription,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(align = Alignment.End)
                        .size(35.dp)
                )
            }
            Spacer(modifier = Modifier.height(11.dp))
            AdminSecondList(navController)
            Spacer(modifier = Modifier.height(11.dp))
            AdminThirdList(navController)
        }
    }
}

@Composable
private fun AdminSecondList(navController: NavController) {
    AdminContent {
        Column {
            AdminItem(
                title = stringResource(id = R.string.admin_question_title),
                content = stringResource(id = R.string.admin_question_content),
                icon = painterResource(id = MiTalkIcon.Question_Icon.drawableId)
            ) {

            }
            AdminListLine()
            AdminItem(
                title = stringResource(id = R.string.admin_graph_title),
                content = stringResource(id = R.string.admin_graph_content),
                icon = painterResource(id = MiTalkIcon.Graph_Icon.drawableId)
            ) {

            }
        }
    }
}

@Composable
private fun AdminThirdList(navController: NavController) {
    AdminContent {
        Column {
            AdminItem(
                title = stringResource(id = R.string.admin_account_issued_title),
                content = stringResource(id = R.string.admin_account_issued_content),
                icon = painterResource(id = MiTalkIcon.Issued_Icon.drawableId)
            ) {
                navController.navigate(AppNavigationItem.AdminIssued.route)
            }
            AdminListLine()
            AdminItem(
                title = stringResource(id = R.string.admin_user_care_title),
                content = stringResource(id = R.string.admin_user_care_content),
                icon = painterResource(id = MiTalkIcon.User_Icon.drawableId)
            ) {
                navController.navigate(AppNavigationItem.AdminUserCare.route)
            }
            AdminListLine()
            AdminItem(
                title = stringResource(id = R.string.admin_message_record_title),
                content = stringResource(id = R.string.admin_message_record_content),
                icon = painterResource(id = MiTalkIcon.Message_Record_Icon.drawableId)
            ) {
                navController.navigate(AppNavigationItem.AdminMessageRecord.route)
            }
            AdminListLine()
            AdminItem(
                title = stringResource(id = R.string.logout),
                content = stringResource(id = R.string.logout),
                icon = painterResource(id = MiTalkIcon.Logout_Icon.drawableId)
            ) {

            }
        }
    }
}

@Composable
private fun AdminListLine() {
    Canvas(
        modifier = Modifier
            .padding(horizontal = 40.dp)
            .fillMaxWidth()
    ) {
        val width = size.width
        val height = size.height

        drawLine(
            start = Offset(x = 0f, y = height),
            end = Offset(x = width, y = height),
            color = MiTalkColor.Gray,
            strokeWidth = 5f
        )
    }
}

@Stable
private val ContentShape = RoundedCornerShape(18.dp)

@Composable
private fun AdminContent(
    modifier: Modifier = Modifier,
    item: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MiTalkColor.White,
                shape = ContentShape
            )
    ) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            item()
        }
    }
}

@Composable
private fun AdminItem(
    title: String,
    content: String,
    icon: Painter,
    onPressed: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(ContentShape)
            .miClickable { onPressed() }
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Spacer(modifier = Modifier.width(16.dp))
        Icon(
            painter = icon,
            contentDescription = "admin item",
            modifier = Modifier.size(17.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Light20NO(text = title)
            Spacer(modifier = Modifier.height(3.dp))
            Regular10NO(
                text = content,
                color = Color(0xFF888888)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ShowAdminMainScreen() {
    val navController = rememberNavController()
    AdminMainScreen(navController = navController, "")
}