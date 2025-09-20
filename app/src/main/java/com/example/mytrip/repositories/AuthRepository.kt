package com.example.mytrip.repositories

import com.example.mytrip.authApi.LoginRequest

interface AuthRepository {
    suspend fun login(request: LoginRequest)
    fun getAccessToken(): String?
    fun logout()
}