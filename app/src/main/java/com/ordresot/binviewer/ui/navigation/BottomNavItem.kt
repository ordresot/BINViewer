package com.ordresot.binviewer.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val label: String, val icon: ImageVector) {
    data object BINSearch : BottomNavItem("bin_search", "Home", Icons.Default.Search)
    data object BINList : BottomNavItem("bin_list", "Settings", Icons.Default.List)

    companion object {
        val items = listOf(BINSearch, BINList)
    }
}