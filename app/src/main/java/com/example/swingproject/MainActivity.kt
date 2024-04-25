package com.example.swingproject

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.swingproject.ui.bottombar.BotNavItem
import com.example.swingproject.ui.theme.SwingProjectTheme
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            val backPressedState by remember {
                mutableStateOf(true)
            }
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            var backPressedTime = 0L
            BackHandler(enabled = backPressedState) {
                if (System.currentTimeMillis() - backPressedTime <= 2000L) {
                    (context as Activity).finish()
                } else {
                    Toast.makeText(context, "한 번 더 누르시면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show()
                }
                backPressedTime = System.currentTimeMillis()
            }
            SwingProjectTheme {
                Scaffold(
                    bottomBar = {
                        NavigationBar(modifier = Modifier.fillMaxHeight(0.1f), containerColor = Color.White) {
                            val currentRoute = navBackStackEntry?.destination?.route
                            BotNavItem().botNavItems().forEachIndexed { i, item ->
                                NavigationBarItem(
                                    colors = NavigationBarItemDefaults.colors(
                                        unselectedIconColor = Color.LightGray,
                                        unselectedTextColor = Color.LightGray,
                                        selectedIconColor = Color.Black,
                                        selectedTextColor = Color.Black,
                                        indicatorColor = Color.White
                                    ),
                                    selected = item.route == currentRoute,
                                    onClick = {
                                        if (currentRoute == Screen.BOOKMARK_VIEW) {
                                            navController.navigate(route = item.route) {
                                                popUpTo(Screen.MAIN_ROUTE) {
                                                    inclusive = true
                                                }
                                            }
                                        } else {
                                            navController.navigate(route = item.route)
                                        }
                                    },
                                    icon = { Icon(imageVector = item.icon, contentDescription = item.name, modifier = Modifier.size(26.dp)) },
                                    label = { Text(text = item.name) },
                                )
                            }
                        }
                    }
                ) {
                    Box(modifier = Modifier.padding(it)) {
                        NavGraph(navController = navController)
                    }
                }
            }
        }
    }
}
