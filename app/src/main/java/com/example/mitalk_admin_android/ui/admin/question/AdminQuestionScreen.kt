package com.example.mitalk_admin_android.ui.admin.question

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.domain.param.AddQuestionParam
import com.example.domain.param.PatchQuestionParam
import com.example.mitalk_admin_android.R
import com.example.mitalk_admin_android.mvi.admin.AdminQuestionSideEffect
import com.example.mitalk_admin_android.ui.admin.header.AdminHeader
import com.example.mitalk_admin_android.ui.util.MiIconButton
import com.example.mitalk_admin_android.util.miClickable
import com.example.mitalk_admin_android.util.observeWithLifecycle
import com.example.mitalk_admin_android.util.theme.*
import com.example.mitalk_admin_android.vm.admin.AdminQuestionViewModel
import kotlinx.coroutines.InternalCoroutinesApi

data class AdminQuestionSimpleData(
    val state: DialogState,
    val id: Long,
    val question: String,
    val answer: String,
)
@OptIn(InternalCoroutinesApi::class)
@Composable
fun AdminQuestionScreen(
    navController: NavController,
    vm: AdminQuestionViewModel = hiltViewModel()
) {
    var dialogVisible by remember { mutableStateOf<AdminQuestionSimpleData?>(null) }

    val container = vm.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow
    
    LaunchedEffect(vm) {
        vm.getAdminQuestionList()
    }
    
    sideEffect.observeWithLifecycle {
        when (it) {
            AdminQuestionSideEffect.ListChanged -> {
                dialogVisible = null
                vm.getAdminQuestionList()
            }
        }
    }

    if (dialogVisible != null) {
        AdminQuestionDialog(
            id = dialogVisible!!.id,
            question = dialogVisible!!.question,
            answer = dialogVisible!!.answer,
            onDismissRequest = {
                dialogVisible = null
            },
            state = dialogVisible!!.state
        ) { id, question, answer ->
           if (dialogVisible!!.state == DialogState.ADD) {
               vm.addQuestion(
                   addQuestionParam = AddQuestionParam(
                       question = question,
                       answer = answer,
                   )
               )
           } else {
               vm.patchAdminQuestion(
                   patchQuestionParam = PatchQuestionParam(
                       id = id,
                       question = question,
                       answer = answer,
                   )
               )
           }
        }
    }
    
    Column {
        AdminHeader(
            navController = navController,
            title = stringResource(id = R.string.admin_question_title),
            findEnabled = false,
        )
        Spacer(modifier = Modifier.height(19.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(13.dp)
        ) {
            items(state.questionList) {
                QuestionContent(
                    id = it.id,
                    question = it.question,
                    answer = it.answer,
                    onIconClick = { data -> dialogVisible = data }
                )
            }
        }
    }
}

@Stable
private val ItemShape = RoundedCornerShape(10.dp)

@Composable
private fun QuestionContent(
    id: Long,
    question: String,
    answer: String,
    onIconClick: (AdminQuestionSimpleData) -> Unit,
) {

    var open by remember { mutableStateOf(false) }
    var targetValue by remember { mutableStateOf(180F) }
    val rotateValue: Float by animateFloatAsState(
        targetValue = targetValue,
        tween(500)
    )

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .background(
                color = MiTalkColor.White,
                shape = ItemShape,
            )
            .border(
                width = 1.dp,
                color = Color(0xFFEBEBEB),
                shape = ItemShape,
            )
            .miClickable(
                rippleEnabled = false,
            ) {
                targetValue = if (!open) {
                    270F
                } else {
                    180F
                }
                open = !open
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        ) {
            Spacer(modifier = Modifier.width(10.dp))

            Bold13NO(
                text = "Q",
                color = Color(0xFF4BE8DE),
            )

            Spacer(modifier = Modifier.width(6.dp))

            Medium13NO(
                modifier = Modifier.width(230.dp),
                text = question,
            )

            Icon(
                painter = painterResource(id = MiTalkIcon.Refactor_Icon.drawableId),
                contentDescription = MiTalkIcon.Refactor_Icon.contentDescription,
                modifier = Modifier
                    .miClickable(
                        rippleEnabled = false
                    ) {
                        onIconClick(
                            AdminQuestionSimpleData(
                                state = DialogState.FIX,
                                id = id,
                                question = question,
                                answer = answer,
                            )
                        )
                    }
            )
            SeeAnswerIcon(rotateValue = rotateValue)
        }

        if (open) {
            Row {
                Spacer(modifier = Modifier.width(10.dp))

                Bold13NO(
                    text = "A",
                    color = Color(0xFFEB5656),
                )

                Spacer(modifier = Modifier.width(6.dp))

                Light11NO(
                    text = answer,
                    modifier = Modifier.padding(end = 21.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
private fun SeeAnswerIcon(
    rotateValue: Float,
) {
    Icon(
        painter = painterResource(id = MiTalkIcon.Back.drawableId),
        contentDescription = MiTalkIcon.Back.contentDescription,
        modifier = Modifier
            .padding(end = 21.dp)
            .fillMaxSize()
            .wrapContentWidth(align = Alignment.End)
            .rotate(rotateValue)
    )
}

