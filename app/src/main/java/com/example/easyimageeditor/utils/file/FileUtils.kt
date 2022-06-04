package com.example.easyimageeditor.utils.file

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.InputStream

object FileUtils {
    suspend fun copyToInternalFile(
        context: Context, iStream: InputStream, fileName: String
    ) = withContext(Dispatchers.IO) {
        File(context.filesDir, fileName).outputStream().use {
            iStream.copyTo(it)
        }
    }

    suspend fun copyAssetToInternalFile(
        context: Context, assetName: String
    ) = withContext(Dispatchers.IO) {
        context.assets.open(assetName).use {
            copyToInternalFile(context, it, assetName)
        }
    }
}