package com.example.cart.presentation.cart

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cart.presentation.component.CartListContent
import com.example.cart.presentation.component.CartListContentShimmer
import com.example.resources.MainColor
import com.example.resources.R
import com.example.resources.WhiteColor
import com.example.utils.presentation.compose.BlankScreen
import com.example.utils.presentation.compose.LoadingBox
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
    val uiState by viewMode.uiState.collectAsStateWithLifecycle(CartUiState.DEFAULT)

    CartScreenContent(
        event = viewMode::onEvent,
        uiState = uiState
    )
}

@Composable
private fun CartScreenContent(
    event: (CartEvent) -> Unit,
    uiState: CartUiState
) {
    Scaffold(
        modifier = Modifier.padding(bottom = 50.dp),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            SimpleTopBar(
                title = stringResource(R.string.title_tab_cart),
                containerColor = MainColor,
                titleContentColor = WhiteColor,
            )
        }
    ) { paddingValues ->
        LoadingBox(
            isLoading = uiState.loading,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            PullRefreshLayout(
                isRefreshing = uiState.refresh,
                onRefresh = { event(CartEvent.Refresh) },
                refreshContent = {
                    CartListContentShimmer()
                }
            ) {
                BlankScreen(
                    painter = painterResource(R.drawable.ic_no_document),
                    title = stringResource(R.string.empty_list_title),
                    visible = uiState.carts.isEmpty()
                )
                if (uiState.carts.isNotEmpty()) {
                    CartListContent(
                        carts = uiState.carts,
                        onCartClick = {/*todo*/ },
                        onPlusClick = { event(CartEvent.OnPlusClick(it)) },
                        onMinusClick = { event(CartEvent.OnMinusClick(it)) },
                        onRemoveClick = { event(CartEvent.OnRemoveClick(it)) }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun CartScreenContentPreview() {
    CartScreenContent(
        event = {},
        uiState = CartUiState.FAKE
    )
}