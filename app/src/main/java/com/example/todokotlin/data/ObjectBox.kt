package com.example.todokotlin.data

import android.content.Context
import com.example.todokotlin.data.models.MyObjectBox
import io.objectbox.BoxStore
import javax.inject.Singleton

@Singleton
object ObjectBox {
    lateinit var boxStore: BoxStore
        private set

    fun init(context: Context) {
        boxStore = MyObjectBox.builder().androidContext(context).build()
    }
}