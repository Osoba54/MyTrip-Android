package com.example.mytrip.viewModels

import com.example.mytrip.UiMessage

sealed class LoginUiState {
    object Idle: LoginUiState()
    object Loading: LoginUiState()
    object Success: LoginUiState()
    data class Error(val message: UiMessage) : LoginUiState()
}