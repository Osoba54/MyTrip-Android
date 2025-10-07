package com.example.mytrip.viewModels

import com.example.mytrip.R
import com.example.mytrip.UiMessage
import com.example.mytrip.model.DomainError

fun mapDomainErrorToUiMessage(error: DomainError): UiMessage {
    return when(error){
        is DomainError.NetworkError -> UiMessage.Resource(R.string.error_network, listOf(error.message))
        is DomainError.UnknownError -> UiMessage.Resource(R.string.error_unknown, listOf(999))
        is DomainError.TokenMissing -> UiMessage.Resource(R.string.error_token_missing)
    }
}