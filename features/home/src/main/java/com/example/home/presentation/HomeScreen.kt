package com.example.home.presentation

import android.widget.Space
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.navigation.HomeGraph
import com.example.utils.domain.Product
import com.example.utils.presentation.compose.LoadingScreenContent
import com.example.utils.presentation.compose.ProductsRow
import com.example.utils.presentation.compose.ProfileHeader
import com.example.utils.presentation.compose.SearchBar
import com.example.resources.WhiteColor
import com.example.utils.presentation.setupSystemBarStyleDefault
import com.example.navigation.LocalNavController
import com.example.resources.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen() {
    val context = LocalContext.current
    context.setupSystemBarStyleDefault(
        statusBarColor = Color.Transparent
    )

    val navController = LocalNavController.current

    val viewModel = koinViewModel<HomeViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val loading = remember {
        mutableStateOf(false)
    }
    val error = remember {
        mutableStateOf<String?>(null)
    }
    val featured = remember {
        mutableStateOf<List<Product>>(emptyList())
    }
    val popularProducts = remember {
        mutableStateOf<List<Product>>(emptyList())
    }
    val categories = remember {
        mutableStateOf<List<String>>(emptyList())
    }

    when (val currentState = uiState) {
        is HomeScreenUiState.Failure -> {
            error.value = currentState.message
            loading.value = false
        }

        is HomeScreenUiState.Initial -> {}
        is HomeScreenUiState.Loading -> {
            loading.value = true
            error.value = null
        }

        is HomeScreenUiState.Success -> {
            loading.value = false
            error.value = null
            featured.value = currentState.featured
            popularProducts.value = currentState.popularProducts
            categories.value = currentState.categories
        }
    }

    HomeScreenContent(
        featured = featured.value,
        popularProducts = popularProducts.value,
        categories = categories.value,
        isLoading = loading.value,
        errorMsg = error.value,
        onProductClick = {
            navController.navigate(HomeGraph.ProductDetailsRoute(it))
        }
    )
}

@Composable
private fun HomeScreenContent(
    featured: List<Product>,
    popularProducts: List<Product>,
    categories: List<String>,
    isLoading: Boolean = false,
    errorMsg: String? = null,
    onProductClick: (Product) -> Unit
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        val bottomScaffold = paddingValues.calculateBottomPadding()
        val topScaffold = paddingValues.calculateTopPadding()

        LazyColumn(
            modifier = Modifier.padding(bottom = bottomScaffold),
            contentPadding = PaddingValues(all = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {

            item {
                Spacer(modifier = Modifier.height(topScaffold))
                ProfileHeader()
            }

            item {
                SearchBar(
                    value = "",
                    onTextChanged = {}
                )
            }

            if (isLoading) {
                item {
                    LoadingScreenContent(text = "Loading...")
                }
            }

            errorMsg?.let { msg ->
                item {
                    Text(
                        text = msg,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            if (categories.isNotEmpty()) {
                item {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(
                            items = categories,
                            key = { it }
                        ) { item ->
                            val isVisible = remember { mutableStateOf(false) }

                            LaunchedEffect(true) {
                                isVisible.value = true
                            }

                            AnimatedVisibility(
                                visible = isVisible.value,
                                enter = fadeIn() + expandVertically()
                            ) {
                                Text(
                                    text = item.apply { replaceFirstChar { it.uppercase() } },
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.SemiBold,
                                    color = WhiteColor,
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(MaterialTheme.colorScheme.primary)
                                        .padding(8.dp)
                                )
                            }
                        }
                    }
                }
            }

            if (featured.isNotEmpty()) {
                item {
                    ProductsRow(
                        products = featured,
                        title = stringResource(R.string.title_featured),
                        onProductClick = onProductClick
                    )
                }
            }

            if (popularProducts.isNotEmpty()) {
                item {
                    ProductsRow(
                        products = popularProducts,
                        title = stringResource(R.string.title_popular),
                        onProductClick = onProductClick
                    )
                }
            }
        }
    }
}












