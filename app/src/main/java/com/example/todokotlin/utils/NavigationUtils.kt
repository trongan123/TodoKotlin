package com.example.todokotlin.utils

import android.annotation.SuppressLint
import androidx.compose.foundation.pager.PagerState
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController

object NavigationUtils {

    @SuppressLint("StaticFieldLeak")
    private var navController: NavController? = null
    private var pagerState: PagerState? = null

    fun setNavController(navController: NavController?) {
        this.navController = navController
    }

    fun setPagerState(pagerState: PagerState?) {
        this.pagerState = pagerState
    }

    fun getNavController(): NavController? {
        return navController
    }

    fun getPagerState(): PagerState? {
        return pagerState
    }

    fun popBackStack() {
        navController?.popBackStack()
    }

    fun navigate(route: String) {
        navController?.navigate(route)
    }

    fun savedStateHandle(id: String, data: Any?) {
        navController?.currentBackStackEntry?.savedStateHandle?.set(id, data)
    }

    fun getSavedStateHandle(): SavedStateHandle? {
       return navController?.previousBackStackEntry?.savedStateHandle
    }

}
