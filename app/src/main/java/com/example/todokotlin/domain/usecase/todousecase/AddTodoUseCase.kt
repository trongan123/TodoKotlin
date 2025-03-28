package com.example.todokotlin.domain.usecase.todousecase

import android.text.TextUtils
import com.example.todokotlin.data.models.Todo
import com.example.todokotlin.domain.irepository.ITodoRepository
import com.example.todokotlin.utils.CoroutineUtils
import java.util.Calendar
import java.util.TimeZone
import javax.inject.Inject

class AddTodoUseCase @Inject constructor(private val repository: ITodoRepository) {

    fun addTodoLists(
        title: String,
        description: String?,
        status: String,
        image: String?
    ): Boolean {
        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(description) || TextUtils.isEmpty(status)) {
            return false
        }

        var itemTodo = Todo(
            0L,
            title,
            description,
            status,
            image,
            getDefaultCalendar()
        )

        CoroutineUtils.launchBackground {
            repository.addTodo(itemTodo)
        }

        return true
    }

    fun getDefaultCalendar(): Calendar {
        return Calendar.getInstance().apply { timeZone = TimeZone.getDefault() }
    }
}