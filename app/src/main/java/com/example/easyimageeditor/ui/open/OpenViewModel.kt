package com.example.easyimageeditor.ui.open

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OpenViewModel @Inject constructor(
    app: Application
) : AndroidViewModel(app) {
}