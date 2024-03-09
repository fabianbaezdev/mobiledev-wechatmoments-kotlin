package com.tws.moments.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Navigation.Routes.HOME
    ) {
        composable(
            route = Navigation.Routes.HOME
        ) {
            HomeScreenDestination()
        }
    }
}

object Navigation {
    object Routes {
        const val HOME = "home"
    }

}