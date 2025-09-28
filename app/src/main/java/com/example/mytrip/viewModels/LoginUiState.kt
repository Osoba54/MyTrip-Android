package com.example.mytrip.viewModels

import androidx.annotation.StringRes

sealed class LoginUiState {
    object Idle: LoginUiState()
    object Loading: LoginUiState()
    object Success: LoginUiState()
    data class Error(@StringRes val messageResId: Int) : LoginUiState()
}