package com.example.todokotlin.presentation.ui.todo

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todokotlin.presentation.ui.view.TodoItem
import com.example.todokotlin.presentation.viewmodel.TodoViewModel
import com.example.todokotlin.R

object TodoList {

    const val route = "todo_list"

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Screen(
        viewModel: TodoViewModel = hiltViewModel(),
        context: Context = LocalContext.current
    ) {
        val listState = rememberLazyListState()
        val todoList by viewModel.todoLists.collectAsState()

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(title = { Text(context.getString(R.string.list_todo)) })
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
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
}
