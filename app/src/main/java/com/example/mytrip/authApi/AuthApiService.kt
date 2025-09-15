package com.example.mytrip.authApi

import com.google.gson.annotations.SerializedName
import retrofit2.http.Body
import retrofit2.Response
import retrofit2.http.POST

interface AuthApiService {
    @POST("api/token/")
    suspend fun login(@Body request: LoginRequest): Response<TokenResponse>

    @POST("api/token/refresh/")
    suspend fun refreshToken(@Body request: RefreshTokenRequest): Response<TokenResponse>
}