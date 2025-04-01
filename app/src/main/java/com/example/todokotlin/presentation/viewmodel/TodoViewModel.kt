package com.example.todokotlin.presentation.viewmodel

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todokotlin.data.models.Todo
import com.example.todokotlin.domain.usecase.todousecase.AddTodoUseCase
import com.example.todokotlin.domain.usecase.todousecase.DetailTodoUseCase
import com.example.todokotlin.domain.usecase.todousecase.ListTodoUseCase
import com.example.todokotlin.domain.usecase.todousecase.PickMediaUseCase
import com.example.todokotlin.utils.BottomNavigationUtils
import com.example.todokotlin.utils.CoroutineUtils
import com.example.todokotlin.utils.NavigationUtils
import com.example.todokotlin.utils.PickMediaUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val listTodoUseCase: ListTodoUseCase,
    private val addTodoUseCase: AddTodoUseCase,
    private val detailTodoUseCase: DetailTodoUseCase,
    private val pickMediaUseCase: PickMediaUseCase
) : ViewModel() {
    private val _todoLists = MutableStateFlow<List<Todo>>(emptyList())
    val todoLists: StateFlow<List<Todo>> = _todoLists

    init {
        handleTodoLists()
    }

    fun handleTodoLists() {
        viewModelScope.launch {
            getTodoLists().collect() { todos ->
                _todoLists.value = todos
            }
        }
    }

    fun getTodoLists(): Flow<List<Todo>> {
        return listTodoUseCase.getTodoLists()
    }

    fun addItemTodo(
        context: Context,
        title: String,
        description: String?,
        status: String,
        image: String?,
        onCallBack: (() -> Unit)? = null,
    ) {
        CoroutineUtils.launchBackground {
            var result = addTodoUseCase.addTodoLists(
                title,
                description,
                status,
                image
            )

            if (result) {
                CoroutineUtils.launchOnMain {
                    Toast.makeText(context, "Add Todo Success!!!", Toast.LENGTH_SHORT).show()
                    BottomNavigationUtils.getPagerState()?.scrollToPage(1)
                    onCallBack?.invoke()
                }
            }
        }
    }

    fun updateItemTodo(
        context: Context,
        todo: Todo
    ) {
        CoroutineUtils.launchBackground {
            var result = detailTodoUseCase.updateTodo(todo)

            if (result) {
                CoroutineUtils.launchOnMain {
                    Toast.makeText(context, "Update Todo Success!!!", Toast.LENGTH_SHORT).show()
                    NavigationUtils.popBackStack()
                }
            }
        }
    }

    fun deleteItemTodo(
        context: Context,
        todo: Todo
    ) {
        CoroutineUtils.launchBackground {
            detailTodoUseCase.deleteTodo(todo)

            CoroutineUtils.launchOnMain {
                Toast.makeText(context, "Remove Todo Success!!!", Toast.LENGTH_SHORT).show()
                NavigationUtils.popBackStack()
            }
        }
    }

    fun getTodoById(todoId: Long): Todo? {
        return detailTodoUseCase.getTodoById(todoId)
    }

    fun openPickMedia(onMediaPicked: (Uri?) -> Unit) {
        pickMediaUseCase.handleOpenPickMedia(
            onGranted = {
                PickMediaUtils.pickMedia(onMediaPicked)
            },
            onDenied = {

            }
        )
    }

}
