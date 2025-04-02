package com.example.todokotlin.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todokotlin.presentation.ui.MainScreen
import com.example.todokotlin.presentation.ui.todo.TodoDetail
import com.example.todokotlin.utils.NavigationUtils

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MainScreen.ROUTE) {
        NavigationUtils.setNavController(navController)
        composable(route = MainScreen.ROUTE) {
            MainScreen.Screen()
        }
        composable(route = TodoDetail.ROUTE) {
            TodoDetail.Screen()
        }
    }
}
