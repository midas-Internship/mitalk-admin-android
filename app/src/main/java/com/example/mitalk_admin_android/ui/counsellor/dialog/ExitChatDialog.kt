package com.example.mitalk_admin_android.ui.counsellor.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MovableContent
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.mitalk_admin_android.util.miClickable
import com.example.mitalk_admin_android.util.theme.Bold20NO
import com.example.mitalk_admin_android.util.theme.MiTalkColor
import com.example.mitalk_admin_android.util.theme.Regular12NO
import com.example.mitalk_admin_android.util.theme.Regular14NO
import com.example.mitalk_admin_android.R


@Composable
fun ExitChatDialog(
    visible: Boolean,
    title: String,
    content: String,
    onDismissRequest: () -> Unit,
    onBtnPressed: () -> Unit
) {
    if (visible) {
        Dialog(onDismissRequest = onDismissRequest) {
            Column(
                modifier = Modifier
                    .width(280.dp)
                    .height(157.dp)
                    .background(color = MiTalkColor.White, shape = RoundedCornerShape(5.dp))
            ) {
                Bold20NO(
                    text = title,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp)
                )
                Regular12NO(
                    text = content,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    color = Color(0xFF4C4C4C)
                )
                Spacer(modifier = Modifier.weight(1F))
                ExitChatBtn(onDismissRequest = onDismissRequest, onBtnPressed = onBtnPressed)
            }
        }
    }
}

@Composable
fun ExitChatBtn(
    onDismissRequest: () -> Unit,
    onBtnPressed: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(1F)
                .fillMaxHeight()
                .background(
                    color = Color(0xFFD0D1DB),
                    shape = RoundedCornerShape(bottomStart = 5.dp)
                )
                .miClickable { onDismissRequest() }
        ) {
            Regular14NO(text = stringResource(id = R.string.cancel))
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(1F)
                .fillMaxHeight()
                .background(
                    color = Color(0xFF4C53FF),
                    shape = RoundedCornerShape(bottomEnd = 5.dp)
                )
                .miClickable { onBtnPressed() }
        ) {
            Regular14NO(text = stringResource(id = R.string.okay), color = MiTalkColor.White)
        }
    }
}