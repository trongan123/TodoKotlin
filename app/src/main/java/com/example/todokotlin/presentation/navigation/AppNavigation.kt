package com.example.todokotlin.presentation.navigation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todokotlin.presentation.ui.MainScreen
import com.example.todokotlin.presentation.ui.todo.AddTodo
import com.example.todokotlin.presentation.ui.todo.TodoDetail
import com.example.todokotlin.presentation.ui.todo.TodoList
import com.example.todokotlin.utils.NavigationUtils

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    MaterialTheme() {
        NavHost(navController = navController, startDestination = MainScreen.route) {
            NavigationUtils.setNavController(navController)
            composable(route = MainScreen.route) {
                MainScreen.Screen()
            }
            composable(route = TodoList.route) {
                TodoList.Screen()
            }
            composable(route = TodoDetail.route) {
                TodoDetail.Screen()
            }
        }
    }
}
