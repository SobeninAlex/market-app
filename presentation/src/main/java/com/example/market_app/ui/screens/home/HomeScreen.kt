package com.example.market_app.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.domain.entity.Product
import com.example.market_app.ui.component.ClickableRoundedColumn
import com.example.market_app.ui.screens.LocalNavController
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen() {
    val navController = LocalNavController.current
    val viewModel = koinViewModel<HomeViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (val currentState = uiState) {
        is HomeScreenUiState.Failure -> {
            Box(
                modifier = Modifier.fillMaxSize().padding(16.dp)
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = currentState.message
                )
            }
        }
        is HomeScreenUiState.Initial -> {}
        is HomeScreenUiState.Loading -> {
            LoadingScreenContent()
        }
        is HomeScreenUiState.Success -> {
            HomeScreenContent(
                products = currentState.products
            )
        }
    }
}

@Composable
fun LoadingScreenContent() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
private fun HomeScreenContent(
    products: List<Product>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(all = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(products) { product ->
            ProductItem(product)
        }
    }
}

@Composable
private fun ProductItem(
    product: Product
) {
    ClickableRoundedColumn(
        onClick = {}
    ) {
        Text(
            text = product.toString()
        )
    }
}

