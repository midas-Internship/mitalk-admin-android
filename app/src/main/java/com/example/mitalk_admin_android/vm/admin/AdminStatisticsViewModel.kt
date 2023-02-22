package com.example.mitalk_admin_android.vm.admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.admin.GetStatisticsDetailUseCase
import com.example.domain.usecase.admin.GetStatisticsUseCase
import com.example.mitalk_admin_android.mvi.admin.AdminStatisticsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class AdminStatisticsViewModel @Inject constructor(
    private val getStatisticsUseCase: GetStatisticsUseCase,
    private val getStatisticsDetailUseCase: GetStatisticsDetailUseCase,
) : ContainerHost<AdminStatisticsState, Unit>, ViewModel() {

    override val container = container<AdminStatisticsState,Unit>(AdminStatisticsState())

    fun getStatisticsList() = intent {
        viewModelScope.launch {
            getStatisticsUseCase()
                .onSuccess {
                    reduce { state.copy(listStatistics = it) }
                }
        }
    }

    fun getStatisticsDetailList(
        id: String
    ) = intent {
        viewModelScope.launch {
            getStatisticsDetailUseCase(id)
                .onSuccess {
                    reduce {
                        state.copy(
                            reviewList = it.reviews,
                            messageList = it.messages
                        )
                    }
                }
        }
    }
}