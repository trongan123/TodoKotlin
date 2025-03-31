package com.example.todokotlin.domain.usecase.searchusecase

import com.example.todokotlin.data.models.Todo
import com.example.todokotlin.domain.irepository.ITodoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val repository: ITodoRepository) {

    fun searchTodoLists(key: String): Flow<List<Todo>> {
        return repository.searchTodoLists(key)
    }
}