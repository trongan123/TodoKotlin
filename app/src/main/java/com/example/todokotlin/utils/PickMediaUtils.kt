package com.example.todokotlin.utils

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable

object PickMediaUtils {

    private lateinit var onMediaPicked: (Uri?) -> Unit?
    private var pickMedia: ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?>? = null

    @Composable
    fun handlePickMedia() {
        pickMedia =
            rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
                onMediaPicked.invoke(uri)
            }
    }

    fun pickMedia(onMediaPicked: (Uri?) -> Unit) {
        this.onMediaPicked = onMediaPicked
        pickMedia?.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }


}