package com.example.composetodo

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.composetodo.screen.TaskScreen

@Composable
fun MainNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Home.screenRoute,
        modifier = modifier
    ){
        composable(route = BottomNavItem.Home.screenRoute){
            TaskScreen()
        }
        composable(route = BottomNavItem.Profile.screenRoute){
            ProfileScreen()
        }
    }
}