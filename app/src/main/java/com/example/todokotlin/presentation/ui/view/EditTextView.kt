package com.example.todokotlin.presentation.ui.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun EditTextView(
    modifier: Modifier?,
    contentText: String? = null,
    contentError: String? = null,
    contentLabel: String? = null,
    contentPlaceholder: String? = null,
    maxLine: Int = 1,
    minLine: Int = 1,
    onValueChange: ((String) -> Unit)? = null,
) {
    var text by remember {
        mutableStateOf("")
    }

    contentText?.let {
        text = it
    }

    var error by remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it
            onValueChange?.invoke(it)
            contentError?.let {
                error = if (text == "") contentError else ""
            }
        },
        modifier = modifier ?: Modifier.fillMaxSize(),
        placeholder = contentPlaceholder?.let { { Text(it) } },
        label = contentLabel?.let { { Text(it) } },
        maxLines = maxLine,
        minLines = minLine
    )
    Text(text = error, color = Color.Red)
}