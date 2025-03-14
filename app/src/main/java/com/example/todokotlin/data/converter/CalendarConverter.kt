package com.example.todokotlin.data.converter
import io.objectbox.converter.PropertyConverter
import java.util.*

class CalendarConverter : PropertyConverter<Calendar, Long> {
    override fun convertToDatabaseValue(entityProperty: Calendar?): Long {
        return entityProperty?.timeInMillis ?: 0
    }

    override fun convertToEntityProperty(databaseValue: Long?): Calendar {
        return Calendar.getInstance().apply { timeInMillis = databaseValue ?: 0 }
    }
}