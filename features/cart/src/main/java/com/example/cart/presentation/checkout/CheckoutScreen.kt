package com.example.cart.presentation.checkout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cart.presentation.component.orderSummaryBlock
import com.example.navigation.CartGraph
import com.example.navigation.LocalNavController
import com.example.resources.MainColor
import com.example.resources.R
import com.example.resources.WhiteColor
import com.example.resources.roundedCornerShape12
import com.example.resources.t3_Bold16
import com.example.utils.presentation.compose.ApplyButton
import com.example.utils.presentation.compose.CircularLoadingIndicator
import com.example.utils.presentation.compose.LoadingBox
import com.example.utils.presentation.compose.PullRefreshLayout
import com.example.utils.presentation.compose.SimpleTopBar
import com.example.utils.presentation.setupSystemBarStyleDefault
import org.koin.androidx.compose.koinViewModel

@Composable
fun CheckoutScreen() {
    val context = LocalContext.current
    context.setupSystemBarStyleDefault(
        statusBarColor = Color.Transparent
    )

    val viewMode = koinViewModel<CheckoutViewModel>()
    val uiState by viewMode.uiState.collectAsStateWithLifecycle()

    CheckoutScreenContent(
        uiState = uiState,
        event = viewMode::onEvent
    )
}

@Composable
private fun CheckoutScreenContent(
    uiState: CheckoutUiState,
    event: (CheckoutEvent) -> Unit
) {
    val navController = LocalNavController.current

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            SimpleTopBar(
                title = stringResource(R.string.title_checkout_screen),
                goBack = { navController.popBackStack() }
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
                onRefresh = { event(CheckoutEvent.Refresh) },
                refreshContent = {
                    //todo: shimmer
                }
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(all = 16.dp)
                ) {
                    orderSummaryBlock(
                        summary = uiState.summary
                    )
                }

                ApplyButton(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    onClick = {},
                    text = "confirm order",
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CheckoutScreenContentPreview() {
    CheckoutScreenContent(
        uiState = CheckoutUiState.FAKE,
        event = {}
    )
}