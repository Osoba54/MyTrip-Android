package com.example.mytrip.authApi

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("access")
    val access: String,
    @SerializedName("refresh")
    val refresh: String
)