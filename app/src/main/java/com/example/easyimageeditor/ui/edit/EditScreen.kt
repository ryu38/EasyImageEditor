package com.example.easyimageeditor.ui.edit

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
import androidx.compose.ui.unit.dp
import com.example.easyimageeditor.ui.base.CustomBottomNavigation
import com.example.easyimageeditor.ui.base.CustomTopAppBar
import com.example.easyimageeditor.ui.base.NavItems

@Composable
fun EditScreen(viewModel: EditViewModel) {
//    val context = LocalContext.current
//    LaunchedEffect(Unit) {
//        viewModel.setSampleImage(context)
//    }
//    viewModel.src?.let {
//        EditMain(viewModel) } ?: NoImageView()
    EditMain(viewModel)
}

@Composable
fun NoImageView() {
    Scaffold(
        topBar = { CustomTopAppBar(title = "Open Image") }
    ) { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .fillMaxHeight()
        ) {

        }
    }
}

@Composable
fun EditMain(viewModel: EditViewModel) {

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
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxHeight()
        ) {
            Image(
                bitmap = viewModel.src!!.asImageBitmap(),
                contentDescription = "target image",
                colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply {
                    setToSaturation(viewModel.tunedParams.saturation)
                })
            )
            Spacer(Modifier.weight(1f))
            when(currentMode) {
                EditModes.TUNE -> Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                    Slider(
                        value = viewModel.tunedParams.saturation,
                        valueRange = 0f..2f,
                        onValueChange = { viewModel.setSaturation(it) }
                    )
                }
            }
            Spacer(Modifier.weight(1f))
        }
    }
}

private enum class EditModes(val label: String, val icon: ImageVector) {
    TUNE("Tune", Icons.Filled.Tune),
    CROP("Crop", Icons.Filled.Crop);

    companion object {
        fun createNavItems(onClick: (EditModes) -> Unit) =
            values().map { NavItems(it.label, it.icon) { onClick(it) } }
    }
}