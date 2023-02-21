package com.example.mitalk_admin_android.vm.admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.admin.GetQuestionListUseCase
import com.example.mitalk_admin_android.mvi.admin.AdminQuestionSideEffect
import com.example.mitalk_admin_android.mvi.admin.AdminQuestionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class AdminQuestionViewModel @Inject constructor(
    private val getQuestionListUseCase: GetQuestionListUseCase,
) : ContainerHost<AdminQuestionState, AdminQuestionSideEffect>, ViewModel() {

    override val container =
        container<AdminQuestionState, AdminQuestionSideEffect>(AdminQuestionState())

    fun getAdminQuestionList() = intent {
        viewModelScope.launch {
            getQuestionListUseCase()
                .onSuccess {
                    reduce { state.copy(questionList = it) }
                }
        }
    }
}