package com.example.mytrip.screens

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mytrip.R
import com.example.mytrip.viewModels.TripViewModel

@Composable
fun TripScreen(
    viewModel: TripViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val importState by viewModel.importState.collectAsState()
    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        uri?.let {
            val contentResolver = context.contentResolver
            val takeFlags: Int = Intent.FLAG_GRANT_READ_URI_PERMISSION
            contentResolver.takePersistableUriPermission(it, takeFlags)

            viewModel.importTrip(it)
        }
    }

    LaunchedEffect(importState) {
        viewModel.showFolderNames()
    }

    val folderNames by viewModel.folderNames.collectAsState()

    Column {
        Button(
            onClick = {
                filePickerLauncher.launch(
                    arrayOf(
                        "application/zip",
                        "application/x-zip-compressed",
                        "multipart/x-zip"
                    )
                )
            }
        ) {
            Icon(Icons.Default.Add, contentDescription = null)
        }

        if(folderNames.isEmpty()){
            Text(stringResource(R.string.text_empty_list))
        } else {
            LazyColumn {
                items(folderNames){ name ->
                    Text(name)
                    HorizontalDivider()
                }
            }
        }

    }
}