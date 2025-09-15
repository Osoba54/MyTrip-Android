package com.example.mytrip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mytrip.components.BottomNavBar
import com.example.mytrip.screens.AccountScreen
import com.example.mytrip.screens.HomeScreen
import com.example.mytrip.screens.TripScreen
import com.example.mytrip.ui.theme.MyTripTheme
import com.example.mytrip.Screen
import com.example.mytrip.screens.LoginScreen
import com.example.mytrip.screens.RegisterScreen
import com.example.mytrip.viewModels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            var screens: List<Screen>
            var isLoggedIn by remember { mutableStateOf(false) }

            LaunchedEffect(isLoggedIn) {
                if (isLoggedIn) {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                } else {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                }
            }

            if (isLoggedIn) {
                screens = listOf(Screen.Home, Screen.Trip, Screen.Account)
                MyTripTheme {
                    Scaffold(
                        bottomBar = { BottomNavBar(navController, screens) }
                    ) { innerPadding ->
                        NavHost(
                            navController = navController,
                            startDestination = Screen.Home.route,
                            modifier = Modifier.padding(innerPadding)
                        ) {
                            composable(Screen.Home.route) { HomeScreen() }
                            composable(Screen.Trip.route) { TripScreen() }
                            composable(Screen.Account.route) {
                                AccountScreen(
                                    onLogoutSuccess = {
                                        isLoggedIn = false
                                    }
                                )
                            }
                        }
                    }
                }

            } else {
//                NIE ZALOGOWANY
//                Osobno aby zwiekszyc readability
                screens = listOf(Screen.Login, Screen.Register)
                MyTripTheme {
                    Scaffold(
                        bottomBar = { BottomNavBar(navController, screens) }
                    ) { innerPadding ->
                        NavHost(
                            navController = navController,
                            startDestination = Screen.Login.route,
                            modifier = Modifier.padding(innerPadding)
                        ) {
                            composable(Screen.Login.route) {
                                LoginScreen(
                                    onLoginSuccess = {
                                        isLoggedIn = true
                                    }
                                )
                            }
                            composable(Screen.Register.route) { RegisterScreen() }
                        }
                    }
                }
            }
        }
    }
}
