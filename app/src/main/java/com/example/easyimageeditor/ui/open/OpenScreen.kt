package com.example.easyimageeditor.ui.open

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

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            composableScope.launch {
                uri?.copyToInternalFile(context)?.let { imageName ->
                    navigate(imageName)
                }
            }
        }
    )

    OpenView(
        onGetSample = {
            composableScope.launch {
                FileUtils.copyAssetToInternalFile(context, SAMPLE_ASSET_IMAGE)
                navigate(SAMPLE_ASSET_IMAGE)
            }
        },
        onImportImage = {
            launcher.launch("image/*")
        }
    )
}