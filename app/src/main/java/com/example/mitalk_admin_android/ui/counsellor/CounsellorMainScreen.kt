package com.example.mitalk_admin_android.ui.counsellor

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mitalk_admin_android.R
import com.example.mitalk_admin_android.ui.util.MiHeader
import com.example.mitalk_admin_android.util.miClickable
import com.example.mitalk_admin_android.util.theme.Bold20NO
import com.example.mitalk_admin_android.util.theme.MiTalkIcon
import com.example.mitalk_admin_android.util.theme.Regular14NO

@Composable
fun CounsellorMainScreen(
    navController: NavController,
) {
    var counselOnOFF by remember { mutableStateOf(false) }
    val counselorImage =
        if (counselOnOFF) painterResource(id = MiTalkIcon.Counsellor_On_Img.drawableId)
        else painterResource(id = MiTalkIcon.Counsellor_Off_Img.drawableId)
    val counselorStateText =
        if(counselOnOFF) stringResource(id = R.string.can_counsel)
        else stringResource(id = R.string.can_not_counsel)

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
                        text = "마이톡",
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
                }
        )
        
        Spacer(modifier = Modifier.height(12.dp))

        Regular14NO(text = counselorStateText)
    }
}

@Composable
@Preview(showBackground = true)
fun ShowCounsellorMainScreen() {
    val navController = rememberNavController()
    CounsellorMainScreen(navController = navController)
}
