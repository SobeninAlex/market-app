package com.example.market_app

import android.annotation.SuppressLint
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
import com.example.cart.presentation.CartScreen
import com.example.details.presentation.ProductDetailsScreen
import com.example.utils.domain.Product
import com.example.home.presentation.HomeScreen
import com.example.navigation.BasketGraph
import com.example.navigation.BottomNavBar
import com.example.navigation.HomeGraph
import com.example.navigation.LocalNavController
import com.example.navigation.ProfileGraph
import com.example.profile.presentation.ProfileScreen
import com.example.utils.event.ObserveAsEvent
import com.example.utils.event.SnackbarController
import com.example.navigation.asType
import com.example.navigation.routeClass
import kotlinx.coroutines.launch
import kotlin.reflect.typeOf

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainNavigationGraph() {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()

    val showBottomBar = when (currentBackStackEntry.routeClass()) {
        HomeGraph.ProductDetailsRoute::class -> false
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
                    startDestination = HomeGraph.HomeRoute,
                ) {
                    composable<HomeGraph.HomeRoute> {
                        HomeScreen()
                    }

                    composable<HomeGraph.ProductDetailsRoute>(
                        typeMap = mapOf(typeOf<Product>() to NavType.asType<Product>())
                    ) { navBackStackEntry ->
                        val args = navBackStackEntry.toRoute<HomeGraph.ProductDetailsRoute>()
                        ProductDetailsScreen(args.product)
                    }
                }

                navigation<BasketGraph>(
                    startDestination = BasketGraph.BasketRoute
                ) {
                    composable<BasketGraph.BasketRoute> {
                        CartScreen()
                    }
                }

                navigation<ProfileGraph>(
                    startDestination = ProfileGraph.ProfileRoute
                ) {
                    composable<ProfileGraph.ProfileRoute> {
                        ProfileScreen()
                    }
                }
            }
        }
    }
}