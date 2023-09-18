package com.example.naversearchcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.naversearchcompose.ui.main.MainRoute
import com.example.naversearchcompose.ui.test.TestRoute

@Composable
fun MainNavHost(
    navHostController: NavHostController = rememberNavController()
){
    NavHost(navController = navHostController, startDestination = MainNavHostRoute.TEST.route){
        composable(MainNavHostRoute.TEST.route) {
            TestRoute {
                navHostController.navigate(MainNavHostRoute.Main.route)
            }
        }

        composable(MainNavHostRoute.Main.route) {
            MainRoute()
        }

    }
}

enum class MainNavHostRoute(
    val route: String
){
    TEST("test"),
    Main("main")
}