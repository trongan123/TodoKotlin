package com.example.todokotlin.presentation.ui.todo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todokotlin.R
import com.example.todokotlin.data.models.Todo
import com.example.todokotlin.presentation.ui.view.IconView
import com.example.todokotlin.utils.NavigationUtils
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

object TodoItem {

    @Composable
    fun Screen(
        item: Todo,
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
                NavigationUtils.savedStateHandle("todoId", item.id)
                NavigationUtils.navigate(TodoDetail.route)
            }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconView(
                    R.drawable.ic_image_default,
                    size = 40,
                    imageUri = item.image
                )
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 10.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = item.title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )

                    item.description?.let {
                        Text(
                            text = it,
                            fontSize = 14.sp,
                            color = Color.Gray,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                Column(
                    modifier = Modifier.fillMaxHeight(),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = item.status,
                        fontSize = 18.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )

                    Text(
                        text = formatCalendarWithCurrentDay(item.createTime),
                        fontSize = 14.sp,
                        color = Color.Gray,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }


    fun formatCalendarWithCurrentDay(calendar: Calendar): String {
        calendar.timeZone = TimeZone.getTimeZone("GMT+0")
        val localTimeZone = TimeZone.getDefault()
        val localLocale = Locale.getDefault()

        val dateFormat = SimpleDateFormat("HH:mm", localLocale).apply {
            timeZone = localTimeZone
        }

        calendar.timeZone = TimeZone.getTimeZone("GMT+0")
        return dateFormat.format(calendar.time)
    }
}
