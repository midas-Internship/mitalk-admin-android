package com.example.mitalk_admin_android.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mitalk_admin_android.util.theme.MiTalkIcon

@Composable
fun LoginScreen(
    navController: NavController,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(42.dp))
        Image(
            painter = painterResource(id = MiTalkIcon.Login_Img.drawableId),
            contentDescription = MiTalkIcon.Login_Img.contentDescription,
        )
        Spacer(modifier = Modifier.height(10.dp))
        Spacer(modifier = Modifier.height(62.dp))

    }
}

@Composable
@Preview(showBackground = true)
fun ShowLoginScreen() {
    val rememberController = rememberNavController()
    LoginScreen(navController = rememberController)
}