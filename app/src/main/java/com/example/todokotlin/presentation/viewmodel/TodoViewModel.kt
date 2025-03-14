package com.example.todokotlin.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.todokotlin.data.models.Todo
import com.example.todokotlin.domain.usecase.TodoUseCase
import com.example.todokotlin.utils.CoroutineUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Calendar
import java.util.TimeZone
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val todoUseCase: TodoUseCase
) : ViewModel() {

    fun getTodoLists (): List<Todo> {
        var list = todoUseCase.getTodoLists()
        for (item in list) {
            Log.e("TAG", "getList 1z: " + item)
        }
        return list
    }

    fun setDemoTodoList(){
        CoroutineUtils.launchBackground {
            var todoList: MutableList<Todo> = mutableListOf()

            for (i in 0..10) {
                var todo =   Todo(
                    0L,
                    "Todo Item : $i",
                    "Todo description",
                    "Todo status",
                    getDefaultCalendar()
                )


                todoList.add(todo)
            }

            todoUseCase.addTodoLists(todoList)
        }
    }

    fun getDefaultCalendar(): Calendar {
        return Calendar.getInstance().apply {
            timeZone = TimeZone.getDefault()
        }
    }
}