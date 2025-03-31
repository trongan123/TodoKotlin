package com.example.todokotlin.data.models

import com.example.todokotlin.data.converter.CalendarConverter
import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import java.util.Calendar

@Entity
data class Todo(
    @Id var id: Long = 0L,
    var title: String,
    var description: String?,
    var status: String,
    var image: String?,
    @Convert(converter = CalendarConverter::class, dbType = Long::class)
    var createTime: Calendar = Calendar.getInstance()
)