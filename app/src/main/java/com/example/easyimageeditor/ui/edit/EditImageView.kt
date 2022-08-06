package com.example.easyimageeditor.ui.edit

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale

data class EditImageUiState(
    val src: Bitmap,
    val saturation: Float,
)

@Composable
fun EditImageView(
    uiState: EditImageUiState,
    modifier: Modifier = Modifier
) {
    Image(
        bitmap = uiState.src.asImageBitmap(),
        contentDescription = "target image",
        contentScale = ContentScale.Fit,
        modifier = modifier,
        colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply {
            setToSaturation(uiState.saturation)
        })
    )
}