package com.example.composetodo

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun TodoNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = "HOME"
    ){
        composable(route = "HOME"){
            TaskScreen()
        }
        composable(route = "PROFILE"){
            ProfileScreen()
        }
    }
}