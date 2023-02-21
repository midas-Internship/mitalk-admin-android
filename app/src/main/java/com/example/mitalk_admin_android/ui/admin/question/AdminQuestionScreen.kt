package com.example.mitalk_admin_android.ui.admin.question

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mitalk_admin_android.R
import com.example.mitalk_admin_android.mvi.admin.AdminQuestionSideEffect
import com.example.mitalk_admin_android.ui.admin.header.AdminHeader
import com.example.mitalk_admin_android.util.observeWithLifecycle
import com.example.mitalk_admin_android.vm.admin.AdminQuestionViewModel
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(InternalCoroutinesApi::class)
@Composable
fun AdminQuestionScreen(
    navController: NavController,
    vm: AdminQuestionViewModel = hiltViewModel()
) {
    val container = vm.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow
    
    LaunchedEffect(vm) {
        vm.getAdminQuestionList()
    }
    
    sideEffect.observeWithLifecycle {
        when (it) {
            AdminQuestionSideEffect.ListChanged -> vm.getAdminQuestionList()
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
            }
        }
    }
}
