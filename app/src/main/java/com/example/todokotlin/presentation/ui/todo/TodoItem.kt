package com.example.todokotlin.presentation.ui.todo

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

object TodoItem {

    @Composable
    fun Screen(
        name: String,
        status: String?,
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(top = 1.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            shape = RoundedCornerShape(0.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
            onClick = {
            }
        ) {
        }
    }
}