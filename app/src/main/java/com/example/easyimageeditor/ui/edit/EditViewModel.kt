package com.example.easyimageeditor.ui.edit

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.example.easyimageeditor.ui.edit.models.TunedParams

@HiltViewModel
class EditViewModel @Inject constructor() : ViewModel() {

    var src by mutableStateOf<Bitmap?>(null)
        private set

    var tunedParams by mutableStateOf(TunedParams())
        private set

    fun setSampleImage(context: Context) {
        viewModelScope.launch {
            src = withContext(Dispatchers.IO) {
                context.assets.open("resort.jpg").use {
                    BitmapFactory.decodeStream(it)
                }
            }
        }
    }

    fun setSaturation(sat: Float) {
        tunedParams = tunedParams.copy(saturation = sat)
    }
}