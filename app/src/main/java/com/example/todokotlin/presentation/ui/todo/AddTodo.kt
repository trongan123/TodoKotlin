package com.example.todokotlin.presentation.ui.todo

import android.Manifest
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.permission.AppPermission
import com.example.permission.PermissionListener
import com.example.todokotlin.R
import com.example.todokotlin.presentation.ui.view.EditTextView
import com.example.todokotlin.presentation.ui.view.IconView
import com.example.todokotlin.presentation.ui.view.SelectBoxView
import com.example.todokotlin.presentation.viewmodel.TodoViewModel
import com.example.todokotlin.utils.CoroutineUtils
import com.example.todokotlin.utils.FileUtils

object AddTodo {
    const val route = "add_todo"

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Screen(
        viewModel: TodoViewModel = hiltViewModel(),
        context: Context = LocalContext.current
    ) {
        var title by remember { mutableStateOf("") }
        var description by remember { mutableStateOf("") }
        var status by remember { mutableStateOf("pending") }
        var imageUri by remember { mutableStateOf<String?>(null) }

        val pickMedia =
            rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
                uri?.let {
                    imageUri = FileUtils.extractFileFromUri(context, uri)
                }
            }
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(title = { Text("Add Todo Item") })
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(start = 20.dp, end = 20.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 50.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    IconView(
                        R.drawable.ic_image_default,
                        size = 110,
                        imageUri = imageUri,
                        onclick = {
                            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                        }
                    )

                    Button(
                        onClick = {
                            requestPermissionReadMedias(object : PermissionListener {
                                override fun onPermissionGranted() {
                                    pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                                }

                                override fun onPermissionDenied(deniedPermissions: List<String>) {

                                }

                            })
                        },
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier.padding(top = 10.dp)
                    ) {
                        Text("Set Image")
                    }
                }

                EditTextView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    contentError = "Field title can't empty",
                    contentPlaceholder = "Title",
                    onValueChange = {
                        title = it
                    }
                )

                EditTextView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    contentError = "Field description can't empty",
                    contentPlaceholder = "Description",
                    maxLine = 3,
                    minLine = 3,
                    onValueChange = {
                        description = it
                    }
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    SelectBoxView(
                        listOf("pending", "completed"),
                        status,
                        onValueSelected = {
                            status = it
                        }
                    )

                }

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        shape = RoundedCornerShape(2.dp), modifier = Modifier,
                        onClick = {
                            viewModel.addItemTodo(
                                context,
                                title,
                                description,
                                status,
                                imageUri.toString()
                            )
                        },
                    ) {
                        Text("ADD TODO")
                    }
                }
            }
        }

    }

    fun requestPermissionReadMedias(permissionListener: PermissionListener) {
        CoroutineUtils.launchBackground {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                AppPermission.create().setListener(permissionListener)
                    .setDeniedMessage(R.string.permission_denied_message)
                    .setPermissions(Manifest.permission.READ_MEDIA_IMAGES).check();
            } else {
                AppPermission.create().setListener(permissionListener)
                    .setDeniedMessage(R.string.permission_denied_message)
                    .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE).check();
            }
        }
    }
}




