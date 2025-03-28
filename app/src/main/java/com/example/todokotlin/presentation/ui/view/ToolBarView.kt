package com.example.todokotlin.presentation.ui.view

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import com.example.todokotlin.R
import com.example.todokotlin.utils.NavigationUtils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolBarView(title: String) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = {NavigationUtils.popBackStack()}) {
                IconView(
                    R.drawable.ic_back_previous,
                    size = 24
                )
            }
        }
    )
}