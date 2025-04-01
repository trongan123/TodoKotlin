package com.example.todokotlin.domain.irepository

import com.example.todokotlin.data.models.Todo
import kotlinx.coroutines.flow.Flow

interface ITodoRepository {

    fun getTodoLists(): List<Todo>
    fun searchTodoLists(key: String): Flow<List<Todo>>
    fun getTodoById(todoId: Long): Todo

    suspend fun addTodo(todo: Todo)

    suspend fun updateTodo(todo: Todo)

    suspend fun addTodoLists(todoList: List<Todo>)

    suspend fun deleteTodo(todo: Todo)
}
