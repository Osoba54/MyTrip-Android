package com.example.mytrip.screens

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun AccountScreen(
    onLogoutSuccess: () -> Unit
) {
    Button(onClick = onLogoutSuccess) { Text("Wyloguj się") }
}