package com.example.todokotlin.domain.usecase.todousecase

import com.example.todokotlin.data.models.Todo
import com.example.todokotlin.domain.irepository.ITodoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ListTodoUseCase @Inject constructor(private val repository: ITodoRepository) {

    fun getTodoLists(): Flow<List<Todo>> {
        return repository.getTodoLists()
    }
}
