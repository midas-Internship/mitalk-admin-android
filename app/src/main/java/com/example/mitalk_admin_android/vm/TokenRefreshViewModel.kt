package com.example.mitalk_admin_android.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.auth.AutoSignInUseCase
import com.example.mitalk_admin_android.util.MutableEventFlow
import com.example.mitalk_admin_android.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TokenRefreshViewModel @Inject constructor(
    private val autoSignInUseCase: AutoSignInUseCase,
): ViewModel() {

    private val _token = MutableEventFlow<Event>()
    val token = _token.asEventFlow()

    fun tokenRefresh() {
        viewModelScope.launch {
            autoSignInUseCase()
                .onSuccess { _token.emit(Event.Success) }
                .onFailure { _token.emit(Event.Fail) }
        }
    }

    sealed class Event {
        object Success : Event()
        object Fail : Event()
    }
}