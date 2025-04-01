package com.example.todokotlin.domain.usecase.todousecase

import android.Manifest
import android.os.Build
import com.example.permission.AppPermission
import com.example.permission.AppPermissionUtils
import com.example.permission.PermissionListener
import com.example.todokotlin.R
import com.example.todokotlin.utils.CoroutineUtils
import javax.inject.Inject

class PickMediaUseCase @Inject constructor() {

    fun handleOpenPickMedia(onGranted: (() -> Unit)?, onDenied: (() -> Unit)?) {
        CoroutineUtils.launchBackground {
            if (AppPermissionUtils.hasPermissionReadMedias()) {
                onGranted?.invoke()
                return@launchBackground
            }

            requestPermissionReadMedias(object : PermissionListener {
                override fun onPermissionGranted() {
                    onGranted?.invoke()
                }

                override fun onPermissionDenied(deniedPermissions: List<String>) {
                    onDenied?.invoke()
                }
            })
        }
    }

    fun requestPermissionReadMedias(permissionListener: PermissionListener) {
        CoroutineUtils.launchBackground {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                AppPermission.create().setListener(permissionListener)
                    .setDeniedMessage(R.string.permission_denied_message)
                    .setPermissions(Manifest.permission.READ_MEDIA_IMAGES).check()
            else
                AppPermission.create().setListener(permissionListener)
                    .setDeniedMessage(R.string.permission_denied_message)
                    .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE).check()

        }
    }
}