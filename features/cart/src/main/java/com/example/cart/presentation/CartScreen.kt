package com.example.cart.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cart.CartEvent
import com.example.navigation.LocalNavController
import com.example.resources.MainColor
import com.example.resources.R
import com.example.resources.WhiteColor
import com.example.utils.presentation.compose.PullRefreshLayout
import com.example.utils.presentation.compose.SimpleTopBar
import com.example.utils.presentation.setupSystemBarStyle
import org.koin.androidx.compose.koinViewModel

@Composable
fun CartScreen() {
    val context = LocalContext.current
    context.setupSystemBarStyle(
        statusBarColor = Color.Transparent,
        navigationBarColor = MaterialTheme.colorScheme.primaryContainer,
        isLightIconsStatusBar = false
    )

    val viewMode = koinViewModel<CartViewModel>()
    val uiState by viewMode.uiState.collectAsStateWithLifecycle()

    CartScreenContent(
        event = viewMode::onEvent,
        uiState = uiState
    )
}

@Composable
private fun CartScreenContent(
    event: (CartEvent) -> Unit,
    uiState: CartScreenUiState
) {
    val navController = LocalNavController.current

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            SimpleTopBar(
                title = stringResource(R.string.title_tab_cart),
                containerColor = MainColor,
                titleContentColor = WhiteColor,
            )
        }
    ) { paddingValues ->
        PullRefreshLayout(
            modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding()),
            isRefreshing = uiState.refresh,
            onRefresh = { event(CartEvent.Refresh) }
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

            }
        }
    }
}