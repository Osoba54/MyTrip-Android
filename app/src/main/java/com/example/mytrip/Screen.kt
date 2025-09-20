package com.example.mytrip

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AppRegistration
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val label: Int, val icon: ImageVector) {
    object Home : Screen("home", R.string.menu_home, Icons.Default.Home)
    object Account : Screen("account", R.string.menu_account, Icons.Default.AccountBox)
    object Trip : Screen("trip", R.string.menu_trip, Icons.Default.DirectionsBus)
    object Login : Screen("login", R.string.menu_login, Icons.Default.AccountCircle)
    object Register : Screen("register", R.string.menu_register, Icons.Default.AppRegistration)
}