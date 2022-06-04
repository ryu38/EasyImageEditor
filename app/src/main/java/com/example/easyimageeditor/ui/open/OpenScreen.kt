package com.example.easyimageeditor.ui.open

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddPhotoAlternate
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.easyimageeditor.SAMPLE_ASSET_IMAGE
import com.example.easyimageeditor.ui.EditNavigator
import com.example.easyimageeditor.ui.base.CustomTopAppBar
import com.example.easyimageeditor.utils.file.FileUtils
import com.example.easyimageeditor.utils.file.copyToInternalFile
import kotlinx.coroutines.launch

@Composable
fun OpenScreen(viewModel: OpenViewModel, navigate: EditNavigator) {
    val context = LocalContext.current
    val composableScope = rememberCoroutineScope()
    Scaffold(
        topBar = { CustomTopAppBar("Open Image") { TopBarActions(
            getSample = {
                composableScope.launch {
                    FileUtils.copyAssetToInternalFile(context, SAMPLE_ASSET_IMAGE)
                    navigate(SAMPLE_ASSET_IMAGE)
                }
            }
        ) } },
        floatingActionButton = { OpenGalleryFAB {
            it?.let { uri ->
                composableScope.launch {
                    uri.copyToInternalFile(context)?.let { imageName ->
                        navigate(imageName)
                    }
                }
            }
        } }
    ) {
        NoDraftsContent()
    }
}

@Composable
fun TopBarActions(getSample: () -> Unit) {
    var expanded by rememberSaveable { mutableStateOf(false) }
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

@Composable
fun OpenGalleryFAB(onResult: (Uri?) -> Unit) {
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent(), onResult)
    FloatingActionButton(onClick = {
        launcher.launch("image/*")
    }) {
        Icon(Icons.Default.AddPhotoAlternate, null)
    }
}

@Composable
fun NoDraftsContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(Icons.Default.AddPhotoAlternate, null,
            modifier = Modifier.size(64.dp))
        Spacer(modifier.height(32.dp))
        Text(
            "Let's open your image.",
            fontSize = 18.sp
        )
    }
}