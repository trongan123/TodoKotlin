package com.example.todokotlin.presentation.ui.view

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.todokotlin.R
import com.example.todokotlin.utils.BottomNavigationUtils
import com.example.todokotlin.utils.CoroutineUtils

@Composable
fun BottomNavigationBar(pagerState: PagerState) {
    BottomNavigationUtils.setSelectedNavigationIndex(rememberSaveable { mutableIntStateOf(1) })
    NavigationBar(
        modifier = Modifier.height(80.dp),
        containerColor = Color.White
    ) {
        navigationItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = BottomNavigationUtils.isSelectedNavigation(pagerState.currentPage),
                onClick = {
                    CoroutineUtils.launchOnMain {
                        pagerState.scrollToPage(index)
                        BottomNavigationUtils.handleSelectedNavigation(index)
                    }
                },
                icon = {
                    IconView(
                        if (BottomNavigationUtils.isSelectedNavigation(index)) item.iconSelected else item.iconDefault,
                        size = 26
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    indicatorColor = Color.White
                )
            )
        }
    }
}

data class NavigationItem(
    val iconDefault: Int,
    val iconSelected: Int
)

val navigationItems =
    listOf(
        NavigationItem(
            R.drawable.ic_nav_search,
            R.drawable.ic_nav_search_selected
        ),
        NavigationItem(
            R.drawable.ic_home,
            R.drawable.ic_home_selected
        ),
        NavigationItem(
            R.drawable.ic_add,
            R.drawable.ic_add_selected
        )
    )
