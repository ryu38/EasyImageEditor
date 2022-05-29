package com.example.easyimageeditor.ui.edit

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Crop
import androidx.compose.material.icons.filled.Tune
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.easyimageeditor.ui.base.CustomBottomNavigation
import com.example.easyimageeditor.ui.base.CustomTopAppBar
import com.example.easyimageeditor.ui.base.NavItems

@Composable
fun EditScreen(viewModel: EditViewModel = hiltViewModel()) {
    val context = LocalContext.current

    var currentMode by rememberSaveable {
        mutableStateOf(EditModes.values().first())
    }

    Scaffold(
        topBar = { CustomTopAppBar(title = "Edit") },
        bottomBar = {
            CustomBottomNavigation(EditModes.createNavItems(
                onClick = { currentMode = it }
            ))
        }
    ) { innerPadding ->
        LaunchedEffect(Unit) {
            viewModel.setSampleImage(context)
        }
        viewModel.src?.let {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxHeight()
            ) {
                Image(
                    bitmap = it.asImageBitmap(),
                    contentDescription = "target image",
                    colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply {
                        setToSaturation(viewModel.tunedParams.saturation)
                    })
                )
                Spacer(Modifier.weight(1f))
                when(currentMode) {
                    EditModes.TUNE -> Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                        TuneSlider(
                            initValue = viewModel.tunedParams.saturation,
                            valueRange = 0f..2f,
                            onValueChanged = { viewModel.setSaturation(it) }
                        )
                    }
                }
                Spacer(Modifier.weight(1f))
            }
        } ?: Text("Loading...")
    }
}

@Composable
fun TuneSlider(
    initValue: Float = 0.5f,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    onValueChanged: (Float) -> Unit
) {
    var value by rememberSaveable { mutableStateOf(initValue) }
    Slider(
        value = value,
        valueRange = valueRange,
        onValueChange = {
            value = it
            onValueChanged(value)
        }
    )
}

private enum class EditModes(val label: String, val icon: ImageVector) {
    TUNE("Tune", Icons.Filled.Tune),
    CROP("Crop", Icons.Filled.Crop);

    companion object {
        fun createNavItems(onClick: (EditModes) -> Unit) =
            values().map { NavItems(it.label, it.icon) { onClick(it) } }
    }
}