package com.example.mytrip.viewModels

import androidx.annotation.StringRes

sealed class ImportState {
    object Idle: ImportState()
    object Loading: ImportState()
    data class Success(@StringRes val messageResId: Int): ImportState()
    data class Error(@StringRes val messageResId: Int): ImportState()
}