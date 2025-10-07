package com.example.mytrip.viewModels

import com.example.mytrip.UiMessage

sealed class LogoutUiState {
    object Idle: LogoutUiState()
    object Loading: LogoutUiState()
    object Success: LogoutUiState()
    data class Error(val message: UiMessage) : LogoutUiState()
}