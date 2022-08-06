package com.example.easyimageeditor.ui.open

import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddPhotoAlternate
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OpenView(
    onGetSample: () -> Unit,
    onImportImage: () -> Unit,
) {
    Scaffold(
        topBar = {
            OpenTopBar(
                getSample = onGetSample
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onImportImage
            ) {
                Icon(Icons.Default.AddPhotoAlternate, null)
            }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(Icons.Default.AddPhotoAlternate, null,
                modifier = Modifier.size(64.dp))
            Spacer(Modifier.height(32.dp))
            Text(
                "Let's open your image.",
                fontSize = 18.sp
            )
        }
    }
}