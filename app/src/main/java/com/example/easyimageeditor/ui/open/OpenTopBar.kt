package com.example.easyimageeditor.ui.open

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.easyimageeditor.ui.base.CustomTopAppBar

@Composable
fun OpenTopBar(
    getSample: () -> Unit
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    CustomTopAppBar(
        "Open Image",
        actions = {
            IconButton(onClick = { expanded = !expanded }) {
                Icon(Icons.Default.MoreVert, null)
            }
            DropdownMenu(
                expanded = expanded, onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(onClick = {
                    expanded = false
                    getSample()
                }) {
                    Text("Use the sample image")
                }
            }
        }
    )
}