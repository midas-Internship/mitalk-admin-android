package com.example.mitalk_admin_android.ui.admin.statistics

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mitalk_admin_android.R
import com.example.mitalk_admin_android.ui.admin.header.AdminHeader
import com.example.mitalk_admin_android.ui.admin.header.addFocusCleaner
import com.example.mitalk_admin_android.util.miClickable
import com.example.mitalk_admin_android.util.theme.Medium07NO
import com.example.mitalk_admin_android.util.theme.Medium12NO
import com.example.mitalk_admin_android.util.theme.MiTalkColor
import com.example.mitalk_admin_android.util.theme.MiTalkIcon

@Stable
private val ItemMainColor = Color(0xFF5F5677)

@Composable
fun AdminStatisticsScreen(
    navController: NavController,
) {
    val focusManager = LocalFocusManager.current
    var findOn by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .addFocusCleaner(focusManager) {
                findOn = false
            }
    )
     {
        AdminHeader(
            navController = navController,
            title = stringResource(id = R.string.admin_graph_title),
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
             modifier = Modifier
                 .padding(32.dp)
                 .fillMaxWidth()
         ) {

         }
         StatisticsItem(name = "홍길동", id = "ㄹ2ㅑㅓ랴ㅗ펼댜럭퍼더ㅜㅈ댈ㅊ", star = 1)

    }
}

@Composable
private fun StatisticsItem(
    name: String,
    id: String,
    star: Int,
) {
    var open by remember { mutableStateOf(false) }
    var targetValue by remember { mutableStateOf(0F) }
    val rotateValue: Float by animateFloatAsState(
        targetValue = targetValue,
        tween(500)
    )

    Column(modifier = Modifier
        .padding(horizontal = 32.dp)
        .fillMaxWidth()
        .background(color = MiTalkColor.Gray)
        .border(
            width = 1.dp,
            color = ItemMainColor,
        )
        .miClickable(
            rippleEnabled = false
        ) {
            targetValue = if (open) 0F else 90F
            open = !open
        },
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Spacer(modifier = Modifier.height(6.dp))
                Row {
                    Medium12NO(
                        text = name,
                        color = Color(0xFF736B88)
                    )
                }
                Spacer(modifier = Modifier.height(2.dp))
                Medium07NO(
                    text = id,
                    color = ItemMainColor,
                )
                Spacer(modifier = Modifier.height(6.dp))
            }
            Image(
                painter = painterResource(id = MiTalkIcon.Navi_Arrow_Icon.drawableId),
                contentDescription = MiTalkIcon.Navi_Arrow_Icon.contentDescription,
                modifier = Modifier
                    .padding(end = 12.dp)
                    .fillMaxWidth()
                    .wrapContentWidth(align = Alignment.End)
                    .rotate(rotateValue),
            )
        }
    }

}