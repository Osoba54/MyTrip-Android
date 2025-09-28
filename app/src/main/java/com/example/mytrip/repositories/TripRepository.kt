package com.example.mytrip.repositories

import android.net.Uri

interface TripRepository {
    suspend fun importTripFromZip(zipUri: Uri): Boolean
}