package com.example.todokotlin.utils

import android.annotation.SuppressLint
import androidx.navigation.NavController

object NavigationUtils {

    @SuppressLint("StaticFieldLeak")
    private var navController: NavController? = null


    fun setNavController(navController: NavController?) {
        this.navController = navController
    }

    fun getNavController(): NavController? {
        return navController
    }

    fun popBackStack(){
        navController?.popBackStack()
    }

    fun navigate(route : String){
        navController?.navigate(route)
    }
}