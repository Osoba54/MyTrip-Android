package com.example.mytrip.screens

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mytrip.R
import com.example.mytrip.viewModels.AccountViewModel
import com.example.mytrip.viewModels.LogoutUiState

@Composable
fun AccountScreen(
    viewModel: AccountViewModel = hiltViewModel(),
    onLogoutSuccess: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState) {
        if(uiState is LogoutUiState.Success){
            onLogoutSuccess()
        }
    }

    Button(onClick = { viewModel.logout() }) { Text(text = stringResource(R.string.button_logout)) }
}