package com.example.easyimageeditor.ui.edit

import android.graphics.Bitmap
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Crop
import androidx.compose.material.icons.filled.Tune
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.easyimageeditor.ui.base.CustomBottomNavigation
import com.example.easyimageeditor.ui.base.CustomTopAppBar
import com.example.easyimageeditor.ui.base.NavItems

data class EditUiState(
    val selectedModeIndex: Int,
    val src: Bitmap,
    val saturation: Float
) {
    fun convertToTune() = EditTuneUiState(
        src = src,
        saturation = saturation,
    )
}

interface EditUiEvent {
    fun onSelectTuneMode()
    fun onSelectCropMode()

    fun onSaturationChange(value: Float)
}

@Composable
fun EditView(
    uiState: EditUiState,
    uiEvent: EditUiEvent,
) {

    Scaffold(
        topBar = { CustomTopAppBar(title = "Edit") },
        bottomBar = {
            CustomBottomNavigation(
                listOf(
                    NavItems(
                        label = "Tune",
                        icon = Icons.Filled.Tune,
                        onClick = { uiEvent.onSelectTuneMode() }
                    ),
                    NavItems(
                        label = "Crop",
                        icon = Icons.Filled.Crop,
                        onClick = { uiEvent.onSelectCropMode() }
                    ),
                ),
                selectedItemIndex = uiState.selectedModeIndex
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (uiState.selectedModeIndex) {
                0 -> EditTuneView(
                    uiState = uiState.convertToTune(),
                    onSaturationChange = { uiEvent.onSaturationChange(it) }
                )
            }
        }
    }
}