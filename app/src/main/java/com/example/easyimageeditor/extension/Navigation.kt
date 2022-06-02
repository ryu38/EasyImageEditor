package com.example.easyimageeditor.extension

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController

@Composable
fun NavBackStackEntry.rememberParentEntry(
    navController: NavController
): NavBackStackEntry {
    val parentId = destination.parent!!.id
    return remember {
        navController.getBackStackEntry(parentId)
    }
}