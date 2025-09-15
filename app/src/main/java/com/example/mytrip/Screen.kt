package com.example.mytrip

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AppRegistration
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object Home : Screen("home", "Home", Icons.Default.Home)
    object Account : Screen("account", "Konto", Icons.Default.AccountBox)
    object Trip : Screen("trip", "Wycieczki", Icons.Default.DirectionsBus)
    object Login : Screen("login", "Logowanie", Icons.Default.AccountCircle)
    object Register : Screen("register", "Rejestracja", Icons.Default.AppRegistration)
}