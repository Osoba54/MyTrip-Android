package com.example.mytrip.data

import androidx.core.net.toUri
import kotlinx.serialization.Serializable

@Serializable
data class PointJSON(
    val name: String?,
    val desc: String?,
    val lat: Double, // latitude
    val lon: Double, // longitude
    val audioUri: List<String>
)

fun PointJSON.toPoint(): Point {
    return Point(
        name = this.name,
        desc = this.desc,
        lat = this.lat,
        lon = this.lon,
        audioUri = this.audioUri.map{ it.toUri() }
    )
}