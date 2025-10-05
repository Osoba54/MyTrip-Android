package com.example.mytrip

import androidx.annotation.StringRes

sealed class UiMessage {
    data class Text(val text: String) : UiMessage()
    data class Resource(@StringRes val resourceId: Int) : UiMessage()
}