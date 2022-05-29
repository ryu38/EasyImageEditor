package com.example.easyimageeditor.ui.base

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun CustomTopAppBar(
    title: String,
) {
    TopAppBar(
        title = { Text(title) },
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp
    )
}