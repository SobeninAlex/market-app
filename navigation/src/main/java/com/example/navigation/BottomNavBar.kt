package com.example.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.navigation.routeClass

@Composable
fun BottomNavBar(
    navController: NavController,
    visible: Boolean
) {
    val tabs = MainTabs

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(tween(1000)) + expandVertically(tween(1000)),
        exit = fadeOut(tween(1000)) + shrinkVertically(tween(1000))
    ) {
        NavigationBar(
            modifier = Modifier.height(100.dp),
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            tonalElevation = 1.dp
        ) {
            val currentBackStackEntry by navController.currentBackStackEntryAsState()
            val navGraphDestination = currentBackStackEntry?.destination?.hierarchy?.first {
                it is NavGraph
            }
            val navGraphClass = navGraphDestination.routeClass()
            val currentTab = tabs.firstOrNull { it.graph::class == navGraphClass }

            tabs.forEach { tab ->
                NavigationBarItem(
                    selected = currentTab == tab,
                    onClick = {
                        if (currentTab != null) {
                            navController.navigate(tab.graph) {
                                popUpTo(currentTab.graph) {
                                    //inclusive = true
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    icon = {
                        Icon(painter = painterResource(tab.icon), contentDescription = null)
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        unselectedIconColor = Color.Gray,
                        indicatorColor = Color.Transparent
                    )
                )
            }
        }
    }
}