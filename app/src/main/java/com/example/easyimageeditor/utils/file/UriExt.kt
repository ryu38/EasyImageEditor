package com.example.easyimageeditor.utils.file

import android.content.Context
import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

val Uri.extension: String?
    get() = lastPathSegment?.split(".")?.last()

suspend fun Uri.copyToInternalFile(
    context: Context
): String? = withContext(Dispatchers.Main) {
    val uri = this@copyToInternalFile
    val filename = uri.lastPathSegment ?: return@withContext null
    withContext(Dispatchers.IO) {
        context.contentResolver.openInputStream(uri)?.use {
            FileUtils.copyToInternalFile(context, it, filename)
            filename
        }
    }
}