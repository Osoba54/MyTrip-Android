package com.example.mytrip.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytrip.R
import com.example.mytrip.UiMessage
import com.example.mytrip.authApi.AuthApiService
import com.example.mytrip.authApi.TokenManager
import com.example.mytrip.repositories.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val authApiService: AuthApiService,
    private val tokenManager: TokenManager
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
                _uiState.value = LogoutUiState.Error(e.message)
            }
        }
    }

    fun fetchPrivateData(){
        viewModelScope.launch {
            try {
                val token = tokenManager.getAccessToken()
                if (token != null){
                    val response = authApiService.getPrivateData("Bearer $token")
                    _privateMessage.value = UiMessage.Text(response.message)
                } else {
                    _privateMessage.value = UiMessage.Resource(R.string.error_unknown)
                }
            } catch (e: Exception){
                _privateMessage.value = UiMessage.Resource(R.string.error_unknown)
            }
        }
    }

}