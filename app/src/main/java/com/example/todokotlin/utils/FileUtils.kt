package com.example.todokotlin.utils

import android.content.Context
import android.net.Uri
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

object FileUtils {

    fun extractFileFromUri(context: Context, uri: Uri): String? {
        try {
            var fileImage = File(context.cacheDir, "${System.currentTimeMillis()}_image.jpeg")

            saveUriToFile(context, uri, fileImage)
            Log.e("TAG", "extractFileFromUri: "+ fileImage.path )
            return fileImage.path
        } catch (_: Exception) {
            return null
        }
    }

    fun saveUriToFile(context: Context, uri: Uri, file: File) {
        try {
            context.contentResolver.openInputStream(uri)?.use { inputStream ->
                FileOutputStream(file).use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
            }
        } catch (_: IOException) {
        }
    }
}