package com.example.mitalk_admin_android.ui.admin.messagerecord

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mitalk_admin_android.ui.util.bottomBorder
import com.example.mitalk_admin_android.util.theme.Light12GM
import com.example.mitalk_admin_android.util.theme.MiTalkColor

@Composable
fun MessageRecordScreen(
    navController: NavController,
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .bottomBorder(
                    modifier = Modifier
                        .fillMaxWidth(),
                    color = Color(0xFFD7D7D7),
                    strokeWidth = 1.dp
                )
        ) {
            Light12GM(text = "2023.02.17")
        }
    }
}

@Composable
private fun MessageRecordItem(
    date: String,
    time: String,
    counsellorName: String,
    userName: String,
    state: String,
    onPressed: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .bottomBorder(
                    modifier = Modifier
                        .fillMaxWidth(),
                    color = Color(0xFFD7D7D7),
                    strokeWidth = 1.dp
                )
        ) {
            Light12GM(text = date)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ShowMessageRecordScreen() {
    val controller = rememberNavController()

    MessageRecordScreen(navController = controller)
}