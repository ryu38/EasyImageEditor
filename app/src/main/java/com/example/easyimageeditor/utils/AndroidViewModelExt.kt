package com.example.easyimageeditor.utils

import android.content.Context
import androidx.lifecycle.AndroidViewModel

val AndroidViewModel.context: Context
    get() = getApplication()