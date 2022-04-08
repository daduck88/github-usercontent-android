package com.duck.githubusercontent.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.duck.githubusercontent.ui.userscontent.UsersContentScreen

@Composable
fun ComposableApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screen.UsersContent.route) {
        composable(route = Screen.UsersContent.route) {
            UsersContentScreen(navController = navController)
        }
        composable(route = Screen.UserContent.route) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")
            requireNotNull(id) { "id parameter wasn't found. Please make sure it's set!" }
            // UserContentScreen(id) TODO make detail screen
        }
    }
}

sealed class Screen(val route: String) {
    object UsersContent : Screen("usersContent")
    object UserContent : Screen("usersContent/{id}") {
        fun createRoute(id: String) = "usersContent/$id"
    }
}