package com.example.mytrip.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytrip.R
import com.example.mytrip.UiMessage
import com.example.mytrip.model.Result
import com.example.mytrip.repositories.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<LogoutUiState>(LogoutUiState.Idle)
    val uiState = _uiState.asStateFlow()
    private val _privateMessage = MutableStateFlow<UiMessage>(UiMessage.Text(""))
    val privateMessage = _privateMessage.asStateFlow()
    fun logout(){
        _uiState.value = LogoutUiState.Loading
        viewModelScope.launch {
            try {
                authRepository.logout()
                _uiState.value = LogoutUiState.Success
            } catch (e: Exception) {
                _uiState.value = if (e.message != null){
                    LogoutUiState.Error(UiMessage.Text(e.message!!))
                } else {
                    LogoutUiState.Error(UiMessage.Resource(R.string.error_unknown, listOf(999)))
                }
            }
        }
    }

    fun fetchPrivateData(){
        viewModelScope.launch {
            when (val result = authRepository.fetchPrivateData()){
                is Result.Success -> {
                    _privateMessage.value = UiMessage.Text(result.data)
                }
                is Result.Error -> {
                    _privateMessage.value = mapDomainErrorToUiMessage(result.error)
                }
            }
        }
    }

}