package com.example.firebaserealtimedbsampleapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebaserealtimedbsampleapp.domain.usecases.GetDeviceStatusUseCase
import com.example.firebaserealtimedbsampleapp.domain.usecases.UpdateDeviceStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getDeviceStatusUseCase: GetDeviceStatusUseCase,
    private val updateDeviceStatusUseCase: UpdateDeviceStatusUseCase
) : ViewModel() {

    private val _connectionStatusValue = MutableStateFlow(false)
    val connectionStatusValue: StateFlow<Boolean>
        get() = _connectionStatusValue

    init {
        getBooleanValue()
    }

    private fun getBooleanValue() {
        getDeviceStatusUseCase().onEach { value ->
            _connectionStatusValue.value = value
        }.launchIn(viewModelScope)
    }

    fun toggleBooleanValue() {
        viewModelScope.launch {
            updateDeviceStatusUseCase(!_connectionStatusValue.value)
        }
    }
}