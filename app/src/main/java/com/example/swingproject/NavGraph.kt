package com.example.swingproject

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.swingproject.ui.bookmark.BookmarkView
import com.example.swingproject.ui.feed.FeedView

object Screen {
    const val MAIN_ROUTE = "MAIN_ROUTE"
    const val FEED_VIEW = "FEED_VIEW"
    const val BOOKMARK_VIEW = "BOOKMARK_VIEW"

}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.FEED_VIEW, route = Screen.MAIN_ROUTE) {
        composable(route = Screen.FEED_VIEW) {
            FeedView()
        }
        composable(route = Screen.BOOKMARK_VIEW) {
            BookmarkView()
        }
    }
}
