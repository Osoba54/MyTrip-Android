package com.example.mytrip.viewModels

import android.app.Application
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytrip.R
import com.example.mytrip.repositories.TripRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TripViewModel @Inject constructor(
    private val application: Application,
    private val tripRepository: TripRepository
) : ViewModel() {

    private val _folderNames = MutableStateFlow<List<String>>(emptyList())
    val folderNames = _folderNames.asStateFlow()

    private val _importState = MutableStateFlow<ImportState>(ImportState.Idle)
    val importState = _importState.asStateFlow()

    init {
        showFolderNames()
    }
    fun importTrip(zipUri: Uri){
        if(_importState.value is ImportState.Loading) return

        _importState.value = ImportState.Loading
        viewModelScope.launch {
            val success = tripRepository.importTripFromZip(zipUri)
            _importState.value = if (success) {
                ImportState.Success(R.string.text_successful_import)
            } else {
                ImportState.Error(R.string.error_import)
            }
        }

    }

    fun showFolderNames(){
        viewModelScope.launch(Dispatchers.IO){
            try{
                val appFilesDir = application.filesDir
                val files = appFilesDir.listFiles { file -> file.isDirectory }
                val names = files?.map {it.name} ?: emptyList()
                _folderNames.update { names }
            } catch (e: Exception){
                _folderNames.update { emptyList() }
            }
        }
    }

}