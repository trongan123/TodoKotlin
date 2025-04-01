package com.example.permission

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.core.content.ContextCompat


object AppPermissionUtils {
    const val REQ_CODE_REQUEST_SETTING = 2000
    private const val PREFS_NAME_PERMISSION = "PREFS_NAME_PERMISSION"
    private const val PREFS_IS_FIRST_REQUEST = "PREFS_IS_FIRST_REQUEST"
    private val context: Context? get() = AppProvider.AppProviderContext.context

    fun isDenied(permission: String): Boolean {
        return !isGranted(permission)
    }

    fun isGranted(permission: String): Boolean {
        return when (permission) {
            Manifest.permission.SYSTEM_ALERT_WINDOW -> Settings.canDrawOverlays(context)
            else -> checkPermission(permission)
        }
    }

    fun checkPermission(permission: String): Boolean {
        return context?.let {
            ContextCompat.checkSelfPermission(
                it,
                permission
            )
        } == PackageManager.PERMISSION_GRANTED
    }

    fun getDeniedPermissions(vararg permissions: String): List<String> {
        return permissions.filter { isDenied(it) }
    }

    private fun getPrefsNamePermission(permission: String): String {
        return "${PREFS_IS_FIRST_REQUEST}_$permission"
    }

    private fun getSharedPreferences(): SharedPreferences? {
        return context?.getSharedPreferences(PREFS_NAME_PERMISSION, Context.MODE_PRIVATE)
    }

    fun getSettingIntent(): Intent {
        return Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).setData(Uri.parse("package:${context?.packageName}"))
    }

    fun setFirstRequest(permissions: Array<String>) {
        permissions.forEach { setFirstRequest(it) }
    }

    private fun setFirstRequest(permission: String) {
        getSharedPreferences()?.edit()?.putBoolean(getPrefsNamePermission(permission), false)
            ?.apply()
    }

    fun hasPermissionReadMedias(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            checkPermission(Manifest.permission.READ_MEDIA_IMAGES) && checkPermission(Manifest.permission.READ_MEDIA_VIDEO)
        } else {
            checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }
}