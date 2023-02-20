package com.example.mitalk_admin_android.ui.admin.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.mitalk_admin_android.util.miClickable
import com.example.mitalk_admin_android.util.theme.*
import com.example.mitalk_admin_android.R

@Composable
fun AdminDialog(
    onCheck: () -> Unit,
    onCancel: () -> Unit,
    title: String,
    name: String,
    id: String,
    hint: String,
    onDismissRequest: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .background(
                    color = MiTalkColor.White,
                    shape = RoundedCornerShape(15.dp)
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Medium17NO(
                text = title,
                color = Color(0xFFC25858)
            )
            Spacer(modifier = Modifier.height(14.dp))
            Column(
                modifier = Modifier
                    .background(
                        color = Color(0xFFE6E6E6)
                    )
                    .padding(horizontal = 30.dp, vertical = 2.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                if (name.isNotEmpty()) {
                    Regular12NO(text = name)
                }
                if (id.isNotEmpty()) {
                    Regular10NO(
                        text = id,
                        color = Color(0xFF707070)
                    )
                }
            }
            Spacer(modifier = Modifier.height(28.dp))
            Regular10NO(
                text = buildAnnotatedString {
                    append("*")
                    append(hint)
                }.toString(),
                color = Color(0xFFCDCDCD)
            )
            Spacer(modifier = Modifier.height(3.dp))
            Row {
                DialogBtn(
                    size = 0.5f,
                    color = Color(0xFFC25858),
                    text = stringResource(id = R.string.yes),
                    shape = RoundedCornerShape(bottomStart = 15.dp)
                ) {
                    onCheck()
                }
                DialogBtn(
                    size = 1f,
                    color = Color(0xFFD8D0D3),
                    text = stringResource(id = R.string.no),
                    shape = RoundedCornerShape(bottomEnd = 15.dp)
                ) {
                    onCancel()
                }
            }
        }
    }
}

@Composable
fun DialogBtn(
    size: Float,
    color: Color,
    text: String,
    shape: RoundedCornerShape,
    onPressed: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(size)
            .height(40.dp)
            .background(
                color = color,
                shape = shape,
            )
            .miClickable { onPressed() },
        contentAlignment = Alignment.Center
    ) {
        Regular16NO(
            text = text,
            color = MiTalkColor.White,
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ShowAdminDialog() {
    AdminDialog(
        onCheck = {  },
        onCancel = {  },
        title = "정말 삭제하시겠습니까?",
        name = "홍길동",
        id = "f13981aa-adca-11ed-afa1-0242ac120002",
        hint = "삭제시 복구가 불가능 합니다"
    ) {

    }
}