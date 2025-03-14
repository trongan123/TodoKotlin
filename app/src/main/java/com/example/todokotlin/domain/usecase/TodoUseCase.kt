package com.example.todokotlin.domain.usecase

import com.example.todokotlin.data.models.Todo
import com.example.todokotlin.domain.irepository.ITodoRepository
import javax.inject.Inject

class TodoUseCase @Inject constructor(
    private val repository: ITodoRepository
) {
    fun getTodoLists(): List<Todo> {
        return repository.getTodoLists()
    }

    fun addTodoLists(list : List<Todo>) {
        return repository.addTodoLists(list)
    }

}