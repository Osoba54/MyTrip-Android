package com.example.mytrip.repositories

import android.content.Context
import com.example.mytrip.R
import com.example.mytrip.authApi.AuthApiService
import com.example.mytrip.authApi.LoginRequest
import com.example.mytrip.authApi.TokenManager
import com.example.mytrip.model.DomainError
import com.example.mytrip.model.Result
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val authApiService: AuthApiService,
    private val tokenManager: TokenManager,
    @ApplicationContext private val context: Context
) : AuthRepository {

    override suspend fun login(request: LoginRequest){
        val response = authApiService.login(request)
        if(response.isSuccessful){
            val tokenResponse = response.body()
            if (tokenResponse != null) {
                tokenManager.saveTokens(tokenResponse.access, tokenResponse.refresh)
            }
        } else {
            val msg: String = when(response.code()) {
                400 -> context.getString(R.string.error_400)
                401 -> context.getString(R.string.error_401)
                403 -> context.getString(R.string.error_403)
                404 -> context.getString(R.string.error_404)
                500 -> context.getString(R.string.error_500)
                503 -> context.getString(R.string.error_503)
                else -> context.getString(R.string.error_unknown, response.code())
            }
            throw Exception("${context.getString(R.string.text_login_failed)}: $msg")
        }
    }

    override fun getAccessToken(): String?{
        return tokenManager.getAccessToken()
    }

    override fun logout(){
        tokenManager.clearTokens()
    }

    override suspend fun fetchPrivateData(): Result<String> {
        return try {
            val token = tokenManager.getAccessToken()
            if (token.isNullOrBlank()) {
                return Result.Error(DomainError.TokenMissing)
            }
            val response = authApiService.getPrivateData("Bearer $token")
            Result.Success(response.message)
        } catch (e: HttpException){
            Result.Error(DomainError.NetworkError(e.code(), e.message()))
        } catch (e: Exception){
            Result.Error(DomainError.UnknownError(e))
        }
    }

}