package com.example.todokotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.todokotlin.presentation.navigation.AppNavigation
import com.example.todokotlin.presentation.theme.TodoKotlinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoKotlinTheme {
                AppNavigation()
            }
        }
    }
}
