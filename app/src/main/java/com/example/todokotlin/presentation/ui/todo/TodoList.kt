package com.example.todokotlin.presentation.ui.todo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todokotlin.presentation.viewmodel.TodoViewModel

object TodoList {
 
    const val route = "todo_list"

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Screen(viewModel: TodoViewModel = hiltViewModel()) {
        val listState = rememberLazyListState()
        val todoList = remember { viewModel.getTodoLists() }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 15.dp,
                    end = 15.dp
                )
                .statusBarsPadding(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                Modifier.fillMaxSize(),
                state = listState
            ) {
                itemsIndexed(todoList, key = { _, item -> item.id }) { _, item ->
                    TodoItem.Screen(item)
                }
            }
        }
    }
}
