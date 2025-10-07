package com.example.mytrip.repositories

import com.example.mytrip.authApi.LoginRequest
import com.example.mytrip.model.Result

interface AuthRepository {
    suspend fun login(request: LoginRequest)
    fun getAccessToken(): String?
    fun logout()
    suspend fun fetchPrivateData(): Result<String>
}