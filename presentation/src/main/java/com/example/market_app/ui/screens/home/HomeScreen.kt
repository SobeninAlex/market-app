package com.example.market_app.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.domain.entity.Product
import com.example.utils.R
import com.example.market_app.ui.component.LoadingScreenContent
import com.example.market_app.ui.component.ProductItem
import com.example.market_app.ui.composition.LocalNavController
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen() {
    val navController = LocalNavController.current
    val viewModel = koinViewModel<HomeViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (val currentState = uiState) {
        is HomeScreenUiState.Failure -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
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
                featured = currentState.featured,
                popularProducts = currentState.popularProducts
            )
        }
    }
}

@Composable
private fun HomeScreenContent(
    featured: List<Product>,
    popularProducts: List<Product>
) {
    ProductsRow(
        products = featured,
        title = stringResource(R.string.title_featured)
    )

    Spacer(modifier = Modifier.height(8.dp))

    ProductsRow(
        products = popularProducts,
        title = stringResource(R.string.title_popular)
    )

}

@Composable
fun ProductsRow(
    products: List<Product>,
    title: String
) {
    Column {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.CenterStart)
            )

            Text(
                text = stringResource(R.string.view_all),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.CenterStart)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(products) { item ->
                ProductItem(item)
            }
        }
    }
}












