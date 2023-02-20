package com.example.mitalk_admin_android.vm.admin

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.admin.GetMessageRecordListUseCase
import com.example.mitalk_admin_android.mvi.admin.AdminMessageRecordSideEffect
import com.example.mitalk_admin_android.mvi.admin.AdminMessageRecordState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class AdminMessageRecordViewModel @Inject constructor(
    private val getMessageRecordListUseCase: GetMessageRecordListUseCase,
) : ContainerHost<AdminMessageRecordState, AdminMessageRecordSideEffect>, ViewModel() {

    override val container = container<AdminMessageRecordState, AdminMessageRecordSideEffect>(AdminMessageRecordState())

    fun getMessageRecordList() = intent {
        viewModelScope.launch {
            getMessageRecordListUseCase()
                .onSuccess {
                    reduce { state.copy(recordList = it)}
                }
        }
    }

    fun openAdminMessageRecordDetail(id: String) {

    }
}