package com.example.mitalk_admin_android.ui.counsellor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun WaitChatDialog(
    visible: Boolean,
    onDismissRequest: () -> Unit
) {
    if (visible) {
        Dialog(onDismissRequest = onDismissRequest) {
            Column(
                modifier = Modifier
                    .width(180.dp)
                    .height(170.dp)
                    .background(Color.White)
            ) {

            }
        }
    }
}