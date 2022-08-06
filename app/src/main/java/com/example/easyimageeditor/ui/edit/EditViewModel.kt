package com.example.easyimageeditor.ui.edit

import android.content.Context
import android.graphics.BitmapFactory
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import java.io.File

@HiltViewModel
class EditViewModel @Inject constructor() : ViewModel() {

    var uiState by mutableStateOf<EditUiState?>(null)
        private set

    fun initState(context: Context, imageName: String) {
        val src = BitmapFactory.decodeFile(File(context.filesDir, imageName).path)
        uiState = EditUiState(
            selectedModeIndex = 0,
            src = src,
            saturation = 1f,
        )
    }

    fun onSelectTuneMode() {
        uiState = uiState?.copy(
            selectedModeIndex = 0
        )
    }

    fun onSelectCropMode() {
        uiState = uiState?.copy(
            selectedModeIndex = 1
        )
    }

    fun onSaturationChange(currentSaturation: Float) {
        uiState = uiState?.copy(
            saturation = currentSaturation
        )
    }
}