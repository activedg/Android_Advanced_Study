package com.example.naversearchcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.naversearchcompose.ui.main.MainRoute
import com.example.naversearchcompose.ui.memo.MemoRoute
import com.example.naversearchcompose.ui.test.TestRoute

@Composable
fun MainNavHost(
    navHostController: NavHostController = rememberNavController()
){
    NavHost(navController = navHostController, startDestination = MainNavHostRoute.TEST.route){
        composable(MainNavHostRoute.TEST.route) {
            TestRoute(
                navigateToMain = { navHostController.navigate(MainNavHostRoute.MAIN.route) },
                navigateToMemo = { navHostController.navigate(MainNavHostRoute.MEMO.route) }
            )
        }

        composable(MainNavHostRoute.MAIN.route) {
            MainRoute()
        }

        composable(MainNavHostRoute.MEMO.route){
            MemoRoute()
        }

    }
}

enum class MainNavHostRoute(
    val route: String
){
    TEST("test"),
    MAIN("main"),
    MEMO("memo")
}