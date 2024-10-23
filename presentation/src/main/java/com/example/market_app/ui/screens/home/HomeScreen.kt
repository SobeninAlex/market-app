package com.example.market_app.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.domain.entity.Product
import com.example.market_app.ui.component.LoadingScreenContent
import com.example.market_app.ui.component.ProductsRow
import com.example.market_app.ui.component.ProfileHeader
import com.example.market_app.ui.component.SearchBar
import com.example.market_app.ui.composition.LocalNavController
import com.example.market_app.ui.theme.LightGray
import com.example.utils.R
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
    LazyColumn(
        contentPadding = PaddingValues(all = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            ProfileHeader()
        }

        item {
            SearchBar(
                value = "",
                onTextChanged = {}
            )
        }

        item {
            if (featured.isNotEmpty()) {
                ProductsRow(
                    products = featured,
                    title = stringResource(R.string.title_featured)
                )
            }
        }

        item {
            if (popularProducts.isNotEmpty()) {
                ProductsRow(
                    products = popularProducts,
                    title = stringResource(R.string.title_popular)
                )
            }
        }
    }
}












