package com.example.todokotlin

import android.app.Application
import com.example.todokotlin.data.ObjectBox

import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        ObjectBox.init(this) // Khởi tạo ObjectBox
    }
}