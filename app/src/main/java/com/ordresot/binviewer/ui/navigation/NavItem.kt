package com.ordresot.binviewer.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavItem(val route: String, val label: String, val icon: ImageVector) {
    data object BINSearchItem : NavItem("bin_search", "Поиск BIN", Icons.Default.Search)
    data object BINListItem : NavItem("bin_list", "История поиска", Icons.AutoMirrored.Filled.List)

    companion object {
        val items = listOf(BINSearchItem, BINListItem)
    }
}