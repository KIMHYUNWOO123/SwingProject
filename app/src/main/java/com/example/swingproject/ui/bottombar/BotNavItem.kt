package com.example.swingproject.ui.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.swingproject.Screen

data class BotNavItem(
    val name: String = "",
    val route: String = "",
    val icon: ImageVector = Icons.Default.Home,
) {
    fun botNavItems(): List<BotNavItem> {
        return listOf(
            BotNavItem(
                name = "피드",
                route = Screen.FEED_VIEW,
                icon = Icons.Default.Menu
            ),
            BotNavItem(
                name = "즐겨찾기",
                route = Screen.BOOKMARK_VIEW,
                icon = Icons.Default.Favorite
            )
        )
    }

}