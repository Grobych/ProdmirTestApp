package com.globa.prodmir

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.globa.prodmir.login.LoginScreen
import com.globa.prodmir.main.MainScreen

@Composable
fun NavController(
    startDestination: NavRoutes
) {
    val navController = rememberNavController()
    val navigateToMain = fun() {
        navController.navigate(route = NavRoutes.Main.name)
    }
    val navigateToLogin = fun() {
        navController.navigate(route = NavRoutes.Login.name)
    }
    NavHost(navController = navController, startDestination = startDestination.name) {
        composable(
            route = NavRoutes.Login.name
        ) {
            LoginScreen(
                navigateToMain = navigateToMain
            )
        }
        composable(
            route = NavRoutes.Main.name
        ) {
            MainScreen(
                navigateToLogin = navigateToLogin
            )
        }
    }
}

enum class NavRoutes {
    Login, Main
}