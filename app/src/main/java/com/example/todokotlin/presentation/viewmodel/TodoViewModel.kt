package com.example.todokotlin.presentation.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.todokotlin.data.models.Todo
import com.example.todokotlin.domain.usecase.todousecase.AddTodoUseCase
import com.example.todokotlin.domain.usecase.todousecase.DetailTodoUseCase
import com.example.todokotlin.domain.usecase.todousecase.ListTodoUseCase
import com.example.todokotlin.utils.CoroutineUtils
import com.example.todokotlin.utils.NavigationUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val listTodoUseCase: ListTodoUseCase,
    private val addTodoUseCase: AddTodoUseCase,
    private val detailTodoUseCase: DetailTodoUseCase
) : ViewModel() {

    fun getTodoLists(): List<Todo> {
        return listTodoUseCase.getTodoLists()
    }

    fun addItemTodo(
        context: Context,
        title: String,
        description: String?,
        status: String,
        image: String?
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
                    NavigationUtils.getPagerState()?.scrollToPage(1)
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

}
