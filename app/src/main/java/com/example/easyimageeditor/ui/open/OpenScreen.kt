package com.example.easyimageeditor.ui

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.example.easyimageeditor.ui.open.OpenViewModel

@Composable
fun OpenScreen(viewModel: OpenViewModel, navigate: EditNavigator) {
    val context = LocalContext.current
    LaunchedEffect(viewModel.imageName) {
        viewModel.imageName?.let {
            navigate(it)
        } ?: viewModel.loadSampleImage()
    }
    Scaffold {
        Text("Loading ...")
    }
}