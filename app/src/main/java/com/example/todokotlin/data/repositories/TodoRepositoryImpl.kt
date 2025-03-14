package com.example.todokotlin.data.repositories

import com.example.todokotlin.data.ObjectBox
import com.example.todokotlin.data.models.Todo
import com.example.todokotlin.domain.irepository.ITodoRepository
import io.objectbox.Box
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TodoRepositoryImpl @Inject constructor() : ITodoRepository {
    private val todoBox: Box<Todo> = ObjectBox.boxStore.boxFor(Todo::class.java)

    override fun getTodoLists(): List<Todo> {
        return todoBox.all
    }

    override fun addTodo(todo: Todo) {
        todoBox.put(todo)
    }

    override fun addTodoLists(todoList: List<Todo>) {
        todoBox.put(todoList)
    }

}