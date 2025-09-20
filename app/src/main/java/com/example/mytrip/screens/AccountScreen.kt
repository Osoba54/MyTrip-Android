package com.example.mytrip.screens

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.mytrip.R

@Composable
fun AccountScreen(
    onLogoutSuccess: () -> Unit
) {
    Button(onClick = onLogoutSuccess) { Text(text = stringResource(R.string.button_logout)) }
}