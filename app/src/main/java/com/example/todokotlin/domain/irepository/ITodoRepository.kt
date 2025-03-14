package com.example.todokotlin.domain.irepository

import com.example.todokotlin.data.models.Todo

interface ITodoRepository {
    fun getTodoLists(): List<Todo>
    fun addTodo(todo: Todo)
    fun addTodoLists(todoList: List<Todo>)
    fun deleteTodo(todo: Todo)
}