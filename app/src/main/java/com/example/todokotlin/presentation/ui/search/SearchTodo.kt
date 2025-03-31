package com.example.todokotlin.presentation.ui.search

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todokotlin.presentation.ui.todo.TodoItem
import com.example.todokotlin.presentation.viewmodel.SearchViewModel

object SearchTodo {

    const val route = "search_todo"

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Screen(viewModel: SearchViewModel = hiltViewModel()) {
        val searchQuery by viewModel.searchQuery.collectAsState()
        val todoList by viewModel.todoList.collectAsState()
        val listState = rememberLazyListState()

        Column(modifier = Modifier) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = {
                    viewModel.updateSearchQuery(it)
                },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
                placeholder = { Text("Search...") },
                modifier = Modifier.run {
                    fillMaxWidth()
                        .padding(16.dp)
                        .background(Color.White, shape = RoundedCornerShape(8.dp))
                }
            )

            if (todoList.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Search Not Found",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            } else {
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