package com.example.mytrip.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    fun logout(){
        _uiState.value = LogoutUiState.Loading
        viewModelScope.launch {
            try {
                authRepository.logout()
                _uiState.value = LogoutUiState.Success
            } catch (e: Exception) {
                _uiState.value = LogoutUiState.Error(e.message)
            }
        }
    }
}