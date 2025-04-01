package com.example.todokotlin.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.todokotlin.presentation.ui.search.SearchTodo
import com.example.todokotlin.presentation.ui.todo.AddTodo
import com.example.todokotlin.presentation.ui.todo.TodoList
import com.example.todokotlin.presentation.ui.view.BottomNavigationBar
import com.example.todokotlin.utils.NavigationUtils

object MainScreen {

    const val route = "mainScreen"

    @Composable
    fun Screen() {
        val pagerState = rememberPagerState(initialPage = 1, pageCount = { 3 })
        NavigationUtils.setPagerState(pagerState)
        Scaffold(
            modifier = Modifier.fillMaxSize().systemBarsPadding(),
            bottomBar = { BottomNavigationBar(pagerState) }) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            ) {
                HorizontalPager(state = pagerState) { page ->
                    when (page) {
                        0 -> SearchTodo.Screen()
                        1 -> TodoList.Screen()
                        2 -> AddTodo.Screen()
                    }
                }
            }
        }
    }
}