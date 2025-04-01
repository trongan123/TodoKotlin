package com.example.todokotlin.utils

import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.MutableIntState

object BottomNavigationUtils {

    private var pagerState: PagerState? = null
    private var selectedNavigationIndex: MutableIntState? = null

    fun setPagerState(pagerState: PagerState?) {
        this.pagerState = pagerState
    }

    fun getPagerState(): PagerState? {
        return pagerState
    }

    fun setSelectedNavigationIndex(selectedNavigationIndex: MutableIntState?) {
        this.selectedNavigationIndex = selectedNavigationIndex
    }

    fun isSelectedNavigation(index: Int): Boolean {
        return this.selectedNavigationIndex?.intValue == index
    }

    fun handleSelectedNavigation(index: Int) {
        selectedNavigationIndex?.intValue = index
    }
}