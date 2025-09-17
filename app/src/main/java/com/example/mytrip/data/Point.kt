package com.example.mytrip.data

import android.net.Uri

data class Point(
    val name: String?,
    val desc: String?,
    val lat: Double, // latitude
    val lon: Double, // longitude
    val audioUri: List<Uri>
)
