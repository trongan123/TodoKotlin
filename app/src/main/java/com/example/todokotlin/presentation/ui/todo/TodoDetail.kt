package com.example.todokotlin.presentation.ui.todo

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todokotlin.R
import com.example.todokotlin.presentation.ui.todo.AddTodo.Screen
import com.example.todokotlin.presentation.ui.view.EditTextView
import com.example.todokotlin.presentation.ui.view.IconView
import com.example.todokotlin.presentation.ui.view.SelectBoxView
import com.example.todokotlin.presentation.ui.view.ToolBarView
import com.example.todokotlin.presentation.viewmodel.TodoViewModel
import com.example.todokotlin.utils.FileUtils
import com.example.todokotlin.utils.NavigationUtils

object TodoDetail {

    const val ROUTE = "todo_detail"

    @Composable
    fun Screen(
        viewModel: TodoViewModel = hiltViewModel(),
        context: Context = LocalContext.current
    ) {
        var todoId = NavigationUtils.getSavedStateHandle()?.get<Long>("todoId")

        if (todoId == null) {
            NavigationUtils.popBackStack()
            return
        }

        var item = viewModel.getTodoById(todoId)

        if (item == null) {
            NavigationUtils.popBackStack()
            return
        }

        var title by remember { mutableStateOf(item.title) }
        var description by remember { mutableStateOf(item.description) }
        var status by remember { mutableStateOf(item.status) }
        var imageUri by remember { mutableStateOf<String?>(item.image) }
        var showDialogRemove by remember { mutableStateOf(false) }

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
                ToolBarView(title)
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
                        .padding(top = 50.dp),
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    contentError = context.getString(R.string.field_title_cant_empty),
                    contentPlaceholder = context.getString(R.string.title),
                    contentLabel = context.getString(R.string.title),
                    contentText = title,
                    onValueChange = {
                        title = it
                    }
                )

                EditTextView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    contentError = context.getString(R.string.field_description_cant_empty),
                    contentPlaceholder = context.getString(R.string.description),
                    contentLabel = context.getString(R.string.description),
                    contentText = description,
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

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 20.dp,
                            top = 10.dp,
                            end = 20.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        shape = RoundedCornerShape(10.dp), modifier = Modifier,
                        onClick = {
                            item.title = title
                            item.description = description
                            item.status = status
                            item.image = imageUri

                            viewModel.updateItemTodo(
                                context,
                                item
                            )
                        },
                    ) {
                        Text(context.getString(R.string.update_todo))
                    }

                    Button(
                        shape = RoundedCornerShape(10.dp), modifier = Modifier,
                        onClick = {
                            showDialogRemove = true
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Red,
                        )
                    )
                    {
                        Text(context.getString(R.string.remove_todo))
                    }

                    DialogRemove(showDialog = showDialogRemove,
                        negativeButton = {
                            showDialogRemove = false
                        },
                        positiveButton = {
                            showDialogRemove = false
                            viewModel.deleteItemTodo(context, item)
                        }
                    )

                }
            }

        }
    }

    @Composable
    fun DialogRemove(
        context: Context = LocalContext.current,
        showDialog: Boolean,
        negativeButton: () -> Unit,
        positiveButton: () -> Unit
    ) {
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { negativeButton() },
                title = { Text(context.getString(R.string.remove)) },
                text = { Text(context.getString(R.string.do_you_want_to_remove_todo)) },
                confirmButton = {
                    Button(onClick = {
                        positiveButton()
                    }) {
                        Text(context.getString(R.string.remove))
                    }
                },
                dismissButton = {
                    Button(onClick = { negativeButton() }) {
                        Text(context.getString(R.string.cancel))
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDatePickerButton() {
    Screen()
}

