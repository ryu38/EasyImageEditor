package com.example.easyimageeditor.ui.edit

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import com.example.easyimageeditor.extension.context
import com.example.easyimageeditor.ui.NavScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.easyimageeditor.ui.edit.models.TunedParams
import java.io.File

@HiltViewModel
class EditViewModel @Inject constructor(
    app: Application,
    savedStateHandle: SavedStateHandle
) : AndroidViewModel(app) {

    val imageName = requireNotNull(
        savedStateHandle.get<String>(NavScreen.Edit.arg)
    )

    var src by mutableStateOf(loadInternalImageFile(imageName))
        private set

    var tunedParams by mutableStateOf(TunedParams())
        private set

    private fun loadInternalImageFile(imageName: String): Bitmap =
        BitmapFactory.decodeFile(File(context.filesDir, imageName).path)

    fun setSaturation(sat: Float) {
        tunedParams = tunedParams.copy(saturation = sat)
    }
}