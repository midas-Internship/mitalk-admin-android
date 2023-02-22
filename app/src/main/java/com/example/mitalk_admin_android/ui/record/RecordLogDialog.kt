package com.example.mitalk_admin_android.ui.record

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog
import com.example.mitalk_admin_android.mvi.RecordDetailState
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
            LazyColumn {
                items(itemList) {
                    Row(horizontalArrangement = Arrangement.SpaceBetween) {
                        Regular12NO(text = it.message)
                        Regular12NO(text = it.time.toChatTime())
                    }
                }
            }
        }
    }
}