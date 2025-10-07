package com.example.mytrip.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mytrip.R
import com.example.mytrip.UiMessage
import com.example.mytrip.viewModels.AccountViewModel
import com.example.mytrip.viewModels.LogoutUiState

@Composable
fun AccountScreen(
    viewModel: AccountViewModel = hiltViewModel(), onLogoutSuccess: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsState()
    val privateMessage by viewModel.privateMessage.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchPrivateData()
    }

    LaunchedEffect(uiState) {
        if (uiState is LogoutUiState.Success) {
            onLogoutSuccess()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { viewModel.logout() },
            modifier = Modifier.padding(16.dp)
        ) { Text(text = stringResource(R.string.button_logout)) }
        Text(
            text = when (val msg = privateMessage) {
                is UiMessage.Text -> msg.text
                is UiMessage.Resource -> stringResource(msg.resourceId, *msg.args.toTypedArray())
            }, modifier = Modifier.padding(16.dp)
        )
    }


}