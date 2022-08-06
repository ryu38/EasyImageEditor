package com.example.easyimageeditor.ui.edit

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Slider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class EditTuneUiState(
    val src: Bitmap,
    val saturation: Float,
)

@Composable
fun EditTuneView(
    uiState: EditTuneUiState,
    onSaturationChange: (Float) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        EditImageView(
            uiState = EditImageUiState(
                src = uiState.src,
                saturation = uiState.saturation,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        Surface(
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Saturation", fontSize = 20.sp)
                Slider (
                    value = uiState.saturation,
                    valueRange = 0f..2f,
                    onValueChange = { onSaturationChange(it) },
                    modifier = Modifier
                        .padding(horizontal = 24.dp, vertical = 16.dp)
                )

            }
        }
    }
}