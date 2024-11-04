package com.example.market_app.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.market_app.navigation.BasketGraph
import com.example.market_app.navigation.BasketGraph.BasketRoute
import com.example.market_app.navigation.BottomNavBar
import com.example.market_app.navigation.HomeGraph
import com.example.market_app.navigation.HomeGraph.HomeRoute
import com.example.market_app.navigation.MainTabs
import com.example.market_app.navigation.ProfileGraph
import com.example.market_app.navigation.ProfileGraph.ProfileRoute
import com.example.market_app.ui.screens.basket.BasketScreen
import com.example.market_app.ui.screens.home.HomeScreen
import com.example.market_app.ui.screens.profile.ProfileScreen
import com.example.market_app.ui.theme.MarketappTheme
import com.example.utils.LocalNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MarketappTheme {
                NavApp()
            }
        }
    }
}

@Composable
private fun NavApp() {
    val navController = rememberNavController()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            BottomNavBar(
                navController = navController,
                tabs = MainTabs
            )
        }
    ) { paddingValues ->
        CompositionLocalProvider(
            LocalNavController provides navController
        ) {
            NavHost(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                navController = navController,
                startDestination = HomeGraph,
                exitTransition = {
                    scaleOut(
                        animationSpec = tween(1000)
                    )
                },
                enterTransition = {
                    scaleIn(
                        animationSpec = tween(1000)
                    )
                },
//                popEnterTransition = {
//                    slideIntoContainer(
//                        AnimatedContentTransitionScope.SlideDirection.Right,
//                        tween(1000)
//                    )
//                },
//                popExitTransition = {
//                    slideOutOfContainer(
//                        AnimatedContentTransitionScope.SlideDirection.Right,
//                        tween(1000)
//                    )
//                }
            ) {
                navigation<HomeGraph>(
                    startDestination = HomeRoute
                ) {
                    composable<HomeRoute> {
                        HomeScreen()
                    }
                }

                navigation<BasketGraph>(
                    startDestination = BasketRoute
                ) {
                    composable<BasketRoute> {
                        BasketScreen()
                    }
                }

                navigation<ProfileGraph>(
                    startDestination = ProfileRoute
                ) {
                    composable<ProfileRoute> {
                        ProfileScreen()
                    }
                }
            }
        }
    }
}