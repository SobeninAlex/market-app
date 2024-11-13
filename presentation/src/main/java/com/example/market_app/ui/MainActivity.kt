package com.example.market_app.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.market_app.navigation.ProfileGraph
import com.example.market_app.navigation.ProfileGraph.ProfileRoute
import com.example.market_app.ui.screens.basket.CartScreen
import com.example.market_app.ui.screens.home.HomeScreen
import com.example.market_app.ui.screens.product_details.ProductDetailsScreen
import com.example.market_app.ui.screens.profile.ProfileScreen
import com.example.market_app.ui.theme.MarketappTheme
import com.example.utils.event.ObserveAsEvent
import com.example.utils.event.SnackbarController
import com.example.utils.navigation.LocalNavController
import com.example.utils.navigation.asType
import com.example.utils.navigation.routeClass
import kotlinx.coroutines.launch
import kotlin.reflect.typeOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarketappTheme {
                NavApp()
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun NavApp() {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()

    val showBottomBar = when (currentBackStackEntry.routeClass()) {
        ProductDetailsRoute::class -> false
        else -> true
    }

    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    ObserveAsEvent(
        flow = SnackbarController.events,
        key1 = snackbarHostState
    ) { event ->
        coroutineScope.launch {
            snackbarHostState.currentSnackbarData?.dismiss()
            val result = snackbarHostState.showSnackbar(
                message = event.message,
                actionLabel = event.snackbarAction?.buttonName,
                duration = SnackbarDuration.Short
            )

            if (result == SnackbarResult.ActionPerformed) {
                event.snackbarAction?.action?.invoke()
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            )
        },
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            AnimatedVisibility(
                visible = showBottomBar,
            ) {
                BottomNavBar(
                    navController = navController
                )
            }
        }
    ) {
        CompositionLocalProvider(
            LocalNavController provides navController
        ) {
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
                        CartScreen()
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