package com.example.easyimageeditor.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.easyimageeditor.ui.edit.EditScreen
import com.example.easyimageeditor.ui.open.OpenScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavScreen.Launch.route
    ) {
        composable(NavScreen.Launch.route) {
            OpenScreen(hiltViewModel()) {
                navController.navigate(NavScreen.Edit.fillInArg(it))
            }
        }
        composable(
            NavScreen.Edit.routeWithArgs,
            arguments = listOf(navArgument(NavScreen.Edit.arg) { NavType.StringType })
        ) {
            EditScreen(hiltViewModel())
        }
    }
}

sealed class NavScreen(val route: String) {
    object Launch : NavScreen("launch")

    object Edit : NavScreen("edit") {
        const val arg = "imageName"
        val routeWithArgs = "$route/{$arg}"
        fun fillInArg(imageName: String) = "$route/$imageName"
    }
}

typealias EditNavigator = (String) -> Unit