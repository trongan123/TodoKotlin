package com.example.todokotlin.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.todokotlin.presentation.ui.todo.TodoDetail
import com.example.todokotlin.presentation.ui.todo.TodoList
import com.example.todokotlin.presentation.ui.view.BottomNavigationBar

object MainScreen {

    const val route = "mainScreen"

    @Composable
    fun Screen() {
        val pagerState = rememberPagerState(pageCount = { 2 })
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                BottomNavigationBar(pagerState)
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            ) {
                HorizontalPager(
                    state = pagerState
                ) { page ->
                    when (page) {
                        0 -> TodoList.Screen()
                        1 -> TodoDetail.Screen()
                    }
                }
            }
        }
    }
}