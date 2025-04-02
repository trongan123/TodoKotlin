package com.example.todokotlin.presentation.ui.todo

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todokotlin.R
import com.example.todokotlin.presentation.ui.view.EditTextView
import com.example.todokotlin.presentation.ui.view.IconView
import com.example.todokotlin.presentation.ui.view.SelectBoxView
import com.example.todokotlin.presentation.viewmodel.TodoViewModel
import com.example.todokotlin.utils.FileUtils

object AddTodo {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Screen(
        viewModel: TodoViewModel = hiltViewModel(),
        context: Context = LocalContext.current
    ) {
        var title by remember { mutableStateOf("") }
        var description by remember { mutableStateOf("") }
        var status by remember { mutableStateOf(context.getString(R.string.pending)) }
        var imageUri by remember { mutableStateOf<String?>(null) }

        var onclickImage: (() -> Unit) = {
            viewModel.openPickMedia { uri ->
                uri?.let {
                    imageUri = FileUtils.extractFileFromUri(context, uri)
                }
            }
        }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(title = { Text(context.getString(R.string.add_todo_item)) })
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(start = 20.dp, end = 20.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    IconView(
                        R.drawable.ic_image_default,
                        size = 110,
                        imageUri = imageUri,
                        onclick = onclickImage
                    )

                    Button(
                        onClick = onclickImage,
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier.padding(top = 10.dp)
                    ) {
                        Text(context.getString(R.string.set_image))
                    }
                }

                EditTextView(
                    contentText = title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    contentError = context.getString(R.string.field_title_cant_empty),
                    contentPlaceholder = context.getString(R.string.title),
                    contentLabel = context.getString(R.string.title),
                    onValueChange = {
                        title = it
                    }
                )

                EditTextView(
                    contentText = description,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    contentError = context.getString(R.string.field_description_cant_empty),
                    contentPlaceholder = context.getString(R.string.description),
                    contentLabel = context.getString(R.string.description),
                    maxLine = 3,
                    minLine = 3,
                    onValueChange = {
                        description = it
                    }
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    SelectBoxView(
                        listOf(
                            context.getString(R.string.pending),
                            context.getString(R.string.completed)
                        ),
                        status,
                        onValueSelected = {
                            status = it
                        }
                    )

                }

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        shape = RoundedCornerShape(20.dp), modifier = Modifier,
                        onClick = {
                            viewModel.addItemTodo(
                                context,
                                title,
                                description,
                                status,
                                imageUri.toString(),
                                onCallBack = {
                                    title = ""
                                    description = ""
                                    status = context.getString(R.string.pending)
                                    imageUri = null
                                }
                            )


                        },
                    ) {
                        Text(context.getString(R.string.add_todo))
                    }
                }
            }
        }
    }

}




