package com.example.easyimageeditor.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.easyimageeditor.ui.edit.EditScreen
import com.example.easyimageeditor.ui.theme.EasyImageProcessorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EasyImageProcessorTheme {
                EditScreen()
            }
        }
    }
}