package com.example.mytrip.model

sealed class DomainError {
    data object TokenMissing : DomainError()
    data class NetworkError(val code: Int, val message: String) : DomainError()
    data class UnknownError(val exception: Exception) : DomainError()
}