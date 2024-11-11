package com.example.market_app.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.domain.entity.Product
import com.example.market_app.navigation.BasketGraph
import com.example.market_app.navigation.BasketGraph.BasketRoute
import com.example.market_app.navigation.BottomNavBar
import com.example.market_app.navigation.HomeGraph
import com.example.market_app.navigation.HomeGraph.HomeRoute
import com.example.market_app.navigation.HomeGraph.ProductDetailsRoute
import com.example.market_app.navigation.MainTabs
import com.example.market_app.navigation.ProfileGraph
import com.example.market_app.navigation.ProfileGraph.ProfileRoute
import com.example.market_app.ui.screens.basket.BasketScreen
import com.example.market_app.ui.screens.home.HomeScreen
import com.example.market_app.ui.screens.product_details.ProductDetailsScreen
import com.example.market_app.ui.screens.profile.ProfileScreen
import com.example.market_app.ui.theme.MarketappTheme
import com.example.utils.LocalNavController
import com.example.utils.asType
import com.example.utils.routeClass
import kotlin.reflect.typeOf

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
    val currentBackStackEntry by navController.currentBackStackEntryAsState()

    CompositionLocalProvider(
        LocalNavController provides navController
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = MaterialTheme.colorScheme.background,
            bottomBar = {
                if (currentBackStackEntry.routeClass() != ProductDetailsRoute::class) {
                    BottomNavBar()
                }
            }
        ) { paddingValues ->
            val bottomPadding = paddingValues.calculateBottomPadding()
            val topPadding = paddingValues.calculateTopPadding()

            NavHost(
                navController = navController,
                startDestination = HomeGraph,
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        tween(1000)
                    )
                },
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        tween(1000)
                    )
                },
                popEnterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        tween(1000)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        tween(1000)
                    )
                }
            ) {
                navigation<HomeGraph>(
                    startDestination = HomeRoute,
                ) {
                    composable<HomeRoute> {
                        HomeScreen()
                    }

                    composable<ProductDetailsRoute>(
                        typeMap = mapOf(typeOf<Product>() to NavType.asType<Product>())
                    ) { navBackStackEntry ->
                        val args = navBackStackEntry.toRoute<ProductDetailsRoute>()
                        ProductDetailsScreen(args.product)
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