package com.example.mytrip.viewModels

sealed class LogoutUiState {
    object Idle: LogoutUiState()
    object Loading: LogoutUiState()
    object Success: LogoutUiState()
    data class Error(val message: String?) : LogoutUiState()
}