package com.example.mytrip.authApi

data class PrivateDataResponse(
    val message: String,
    val username: String,
    val email: String?,
    val firstName: String?,
    val lastName: String?,
    val groups: List<String>
)
