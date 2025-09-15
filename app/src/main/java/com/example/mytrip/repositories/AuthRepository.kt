package com.example.mytrip.repositories

import com.example.mytrip.authApi.AuthApiService
import com.example.mytrip.authApi.LoginRequest
import com.example.mytrip.authApi.TokenManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val authApiService: AuthApiService,
    private val tokenManager: TokenManager
) {

    suspend fun login(request: LoginRequest){
        val response = authApiService.login(request)
        if(response.isSuccessful){
            val tokenResponse = response.body()
            if (tokenResponse != null) {
                tokenManager.saveTokens(tokenResponse.access, tokenResponse.refresh)
            }
        } else {
            throw Exception("Nie udane logowanie: ${response.code()}")
        }
    }

    fun getAccessToken(): String?{
        return tokenManager.getAccessToken()
    }

    fun logout(){
        tokenManager.clearTokens()
    }

}