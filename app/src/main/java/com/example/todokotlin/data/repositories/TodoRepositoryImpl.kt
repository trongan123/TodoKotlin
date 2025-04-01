package com.example.todokotlin.data.repositories

import com.example.todokotlin.data.ObjectBox
import com.example.todokotlin.data.models.Todo
import com.example.todokotlin.data.models.Todo_
import com.example.todokotlin.domain.irepository.ITodoRepository
import io.objectbox.Box
import io.objectbox.query.QueryBuilder
import io.objectbox.query.QueryBuilder.StringOrder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TodoRepositoryImpl @Inject constructor() : ITodoRepository {
    private val todoBox: Box<Todo> = ObjectBox.boxStore.boxFor(Todo::class.java)

    override fun getTodoLists(): Flow<List<Todo>> = callbackFlow {
        val query = todoBox.query()
            .order(Todo_.createTime, QueryBuilder.DESCENDING)
            .build()

        val subscription = query.subscribe()
            .observer { todos ->
                trySend(todos)
            }

        awaitClose { subscription.cancel() } 
    }.flowOn(Dispatchers.IO)

    override fun searchTodoLists(key: String): Flow<List<Todo>> = flow {
        val result = todoBox
            .query(Todo_.title.contains(key, StringOrder.CASE_INSENSITIVE))
            .order(Todo_.createTime, QueryBuilder.DESCENDING)
            .build()
            .find()
        emit(result)
    }.flowOn(Dispatchers.IO)

    override fun getTodoById(todoId: Long): Todo {
        return todoBox[todoId]
    }

    override suspend fun addTodo(todo: Todo) {
        todoBox.put(todo)
    }

    override suspend fun updateTodo(todo: Todo) {
        val existingTodo = todoBox.query(Todo_.id.equal(todo.id)).build().findFirst()

        if (existingTodo == null) {
            return
        }

        todoBox.put(todo)
    }

    override suspend fun addTodoLists(todoList: List<Todo>) {
        todoBox.put(todoList)
    }

    override suspend fun deleteTodo(todo: Todo) {
        todoBox.remove(todo.id)
    }
}
