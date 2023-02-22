package com.example.mitalk_admin_android.ui.admin.question

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.mitalk_admin_android.ui.util.MiIconButton
import com.example.mitalk_admin_android.R
import com.example.mitalk_admin_android.util.miClickable
import com.example.mitalk_admin_android.util.theme.*

sealed class DialogState {
    object FIX : DialogState()
    object ADD : DialogState()
}

@Stable
private val DialogShape = RoundedCornerShape(12.dp)

@Composable
fun AdminQuestionDialog(
    id: Long,
    question: String,
    answer: String,
    state: DialogState,
    onDismissRequest: () -> Unit,
    onClick: (Long, String, String) -> Unit,
) {
    var questionText by remember { mutableStateOf(question) }
    var answerText by remember { mutableStateOf(answer) }

    val btnText =
        if(state == DialogState.ADD) stringResource(id = R.string.registration)
        else stringResource(id = R.string.refactor)

    val clickEnabled =
        (answerText != answer || questionText != question) &&
            answerText.isNotEmpty() && questionText.isNotEmpty()
    val btnColor =
        if (clickEnabled) Color(0x80000000)
        else Color(0x1A000000)
    
    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 40.dp)
                .fillMaxWidth()
                .background(
                    color = MiTalkColor.White,
                    shape = DialogShape,
                )
                .border(
                    width = 1.dp,
                    color = Color(0x1A000000),
                    shape = DialogShape,
                )
        ) {
            Spacer(modifier = Modifier.height(18.dp))
            Icon(
                painter = painterResource(id = MiTalkIcon.Cancel_Icon.drawableId),
                contentDescription = MiTalkIcon.Cancel_Icon.contentDescription,
                modifier = Modifier
                    .padding(end = 20.dp)
                    .fillMaxWidth()
                    .wrapContentWidth(align = Alignment.End)
                    .miClickable(
                        rippleEnabled = false
                    ) {
                        onDismissRequest()
                    }
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 30.dp)
                    .fillMaxWidth()
            ) {
                Bold12NO(text = stringResource(id = R.string.question))
                Spacer(modifier = Modifier.height(6.dp))
                DialogTextField(
                    hint = stringResource(id = R.string.hint_question),
                    value = questionText,
                    onValueChange = { questionText = it }
                )
                Spacer(modifier = Modifier.height(23.dp))
                Bold12NO(text = stringResource(id = R.string.answer))
                Spacer(modifier = Modifier.height(6.dp))
                DialogTextField(
                    hint = stringResource(id = R.string.hint_answer),
                    value = answerText,
                    onValueChange = { answerText = it }
                )
                Spacer(modifier = Modifier.height(20.dp))
                Bold12NO(
                    text = btnText,
                    color = btnColor,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(align = Alignment.CenterHorizontally)
                        .miClickable(
                            rippleEnabled = false
                        ) {
                            if (clickEnabled) {
                                onClick(
                                    id, questionText, answerText
                                )
                            }
                        }
                )
                Spacer(modifier = Modifier.height(77.dp))
            }
        }
    }
}

@Composable
private fun DialogTextField(
    modifier: Modifier = Modifier,
    hint: String,
    value: String,
    onValueChange: (String) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color(0x4DB5B5B5),
                shape = RoundedCornerShape(5.dp),
            )
    ) {
        BasicTextField(
            modifier = modifier
                .padding(horizontal = 10.dp, vertical = 10.dp)
                .fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            textStyle = MiTalkAdminTypography.regular10NO,
            decorationBox = @Composable {
                it()

                if (value.isEmpty()) {
                    Regular10NO(
                        text = hint,
                        color = Color(0x4D000000)
                    )
                }
            }
        )
    }
}