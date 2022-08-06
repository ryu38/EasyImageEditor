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
        startDestination = NavDest.Launch.route
    ) {
        composable(
            NavDest.Launch.route
        ) {
            OpenScreen(
                viewModel = hiltViewModel(),
                navigate = {
                    navController.navigate(NavDest.Edit.fillInArg(it))
                }
            )
        }
        composable(
            NavDest.Edit.route,
            arguments = listOf(
                navArgument(NavDest.Edit.arg) { NavType.StringType }
            )
        ) {
            val args = requireNotNull(it.arguments)
            EditScreen(
                viewModel = hiltViewModel(),
                imageName = requireNotNull(args.getString(NavDest.Edit.arg))
            )
        }
    }
}

sealed class NavDest {
    abstract val route: String

    object Launch : NavDest() {
        override val route = "launch"
    }

    object Edit : NavDest() {
        private const val path = "edit"
        const val arg = "imageName"
        override val route = "$path/{$arg}"
        fun fillInArg(imageName: String) = "$path/$imageName"
    }
}

typealias EditNavigator = (imageName: String) -> Unit