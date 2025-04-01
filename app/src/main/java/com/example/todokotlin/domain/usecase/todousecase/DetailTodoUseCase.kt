package com.example.todokotlin.domain.usecase.todousecase

import android.text.TextUtils
import com.example.todokotlin.data.models.Todo
import com.example.todokotlin.domain.irepository.ITodoRepository
import com.example.todokotlin.utils.CoroutineUtils
import javax.inject.Inject

class DetailTodoUseCase @Inject constructor(private val repository: ITodoRepository) {

    fun getTodoById(todoId: Long): Todo? {
        return repository.getTodoById(todoId)
    }

    fun updateTodo(
        todo: Todo
    ): Boolean {
        if (TextUtils.isEmpty(todo.title) || TextUtils.isEmpty(todo.description) || TextUtils.isEmpty(
                todo.status
            )
        ) {
            return false
        }

        CoroutineUtils.launchBackground {
            repository.updateTodo(todo)
        }

        return true
    }

    fun deleteTodo(todo: Todo) {
        CoroutineUtils.launchBackground {
            repository.deleteTodo(todo)
        }
    }

}