package com.example.todokotlin.presentation.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todokotlin.R

@Composable
fun SelectBoxView(
    selectList: List<String>,
    selected: String? = null,
    onValueSelected: ((String) -> Unit)? = null
) {
    var expanded by remember { mutableStateOf(false) }
    var itemSelected by remember { mutableStateOf("") }

    itemSelected = if (selected == null)
        selectList[0]
    else
        selected

    Card(
        modifier = Modifier,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        onClick = { expanded = !expanded }) {

        Row(
            modifier = Modifier.padding(
                top = 10.dp,
                bottom = 10.dp,
                start = 5.dp,
                end = 5.dp
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = itemSelected,
                fontSize = 18.sp
            )

            IconView(
                R.drawable.ic_select_box,
                size = 24,
                start = 5.dp
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                selectList.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(item) },
                        onClick = {
                            expanded = false
                            itemSelected = item

                            onValueSelected?.invoke(item)
                        }
                    )

                }
            }
        }
    }
}