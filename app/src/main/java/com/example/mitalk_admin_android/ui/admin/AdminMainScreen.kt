package com.example.mitalk_admin_android.ui.admin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import com.example.mitalk_admin_android.R
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mitalk_admin_android.util.theme.MiTalkColor
import com.example.mitalk_admin_android.util.theme.MiTalkIcon

@Composable
fun AdminMainScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        AdminContent {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column {

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
        }
    }
}

@Composable
private fun AdminContent(
    item: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .background(
                color = MiTalkColor.White,
                shape = RoundedCornerShape(18.dp)
            )
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        item()
        Spacer(modifier = Modifier.height(10.dp))
    }
}