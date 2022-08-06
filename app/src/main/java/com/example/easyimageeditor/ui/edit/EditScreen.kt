package com.example.easyimageeditor.ui.edit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext

@Composable
fun EditScreen(
    viewModel: EditViewModel,
    imageName: String,
) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.initState(context, imageName)
    }

    viewModel.uiState?.let { uiState ->
        EditView(
            uiState = uiState,
            uiEvent = object : EditUiEvent {
                override fun onSelectTuneMode() {
                    viewModel.onSelectTuneMode()
                }
                override fun onSelectCropMode() {
                    viewModel.onSelectCropMode()
                }

                override fun onSaturationChange(value: Float) {
                    viewModel.onSaturationChange(value)
                }

            }
        )
    }
}