package com.example.easyimageeditor.ui.open

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.easyimageeditor.extension.context
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class OpenViewModel @Inject constructor(
    app: Application
) : AndroidViewModel(app) {

    var imageName by mutableStateOf<String?>(null)
        private set

    fun loadSampleImage() {
        viewModelScope.launch(Dispatchers.IO) {
            context.assets.open(SAMPLE_IMAGE).use { iStream ->
                val file = File(context.filesDir, SAMPLE_IMAGE)
                file.outputStream().use {
                    iStream.copyTo(it)
                }
            }
            imageName = SAMPLE_IMAGE
        }
    }

    companion object {
        const val SAMPLE_IMAGE = "resort.jpg"
    }
}