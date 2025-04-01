package com.example.todokotlin.presentation.ui.search

import android.content.Context
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todokotlin.R
import com.example.todokotlin.presentation.ui.view.TodoItem
import com.example.todokotlin.presentation.viewmodel.SearchViewModel

object SearchTodo {

    const val route = "search_todo"

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Screen(
        viewModel: SearchViewModel = hiltViewModel(),
        content: Context = LocalContext.current
    ) {
        val searchQuery by viewModel.searchKey.collectAsState()
        val todoList by viewModel.todoList.collectAsState()
        val listState = rememberLazyListState()

        Column(modifier = Modifier) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = {
                    viewModel.updateSearchKey(it)
                },
                leadingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = ""
                    )
                },
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
                        content.getString(R.string.search_not_found),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                }
            } else {
                LazyColumn(
                    Modifier
                        .fillMaxSize()
                        .padding(horizontal = 10.dp),
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