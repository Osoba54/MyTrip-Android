package com.example.mytrip.viewModels

sealed class LoginUiState {
    object Idle: LoginUiState()
    object Loading: LoginUiState()
    object Success: LoginUiState()
    data class Error(val message: String?, val messageResId: Int?) : LoginUiState()
}