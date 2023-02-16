package com.example.mitalk_admin_android.ui.counsellor

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mitalk_admin_android.ui.util.MiHeader
import com.example.mitalk_admin_android.util.theme.Bold20NO
import com.example.mitalk_admin_android.util.theme.MiTalkIcon

@Composable
fun CounsellorMainScreen(
    navController: NavController,
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


}

@Composable
@Preview(showBackground = true)
fun ShowCounsellorMainScreen() {
    val navController = rememberNavController()
    CounsellorMainScreen(navController = navController)
}