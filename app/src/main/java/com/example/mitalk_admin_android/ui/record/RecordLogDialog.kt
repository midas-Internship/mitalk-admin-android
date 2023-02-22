package com.example.mitalk_admin_android.ui.record

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.mitalk_admin_android.mvi.RecordDetailState
import com.example.mitalk_admin_android.util.theme.MiTalkColor
import com.example.mitalk_admin_android.util.theme.Regular12NO
import com.example.mitalk_admin_android.util.toChatTime

@Composable
fun RecordLogDialog(
    visible: Boolean,
    onDismissRequest: () -> Unit,
    itemList: List<RecordDetailState.MessageRecordData.MessageData>
) {
    if (visible) {
        Dialog(onDismissRequest = onDismissRequest) {
            LazyColumn(
                modifier = Modifier
                    .background(
                        color = MiTalkColor.White,
                        shape = RoundedCornerShape(5.dp)
                    )
                    .padding(horizontal = 15.dp, vertical = 5.dp)
            ) {
                items(itemList) {
                    Column {
                        Spacer(modifier = Modifier.height(5.dp))
                        Row(
                            modifier = Modifier
                                .width(230.dp)
                                .heightIn(max = 200.dp)
                        ) {
                            Regular12NO(text = it.message)
                            Regular12NO(text = it.time.toChatTime())
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                    }
                }
            }
        }
    }
}