package com.example.mytrip.data

data class Trip(
    val name: String,
    val desc: String?,
    val creationDate: String, //dd.mm.rrrr
    val points: List<Point>
)
