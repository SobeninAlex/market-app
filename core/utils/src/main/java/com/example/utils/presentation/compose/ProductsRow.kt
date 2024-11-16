package com.example.utils.presentation.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.utils.domain.Product
import com.example.resources.R

@Composable
fun ProductsRow(
    products: List<Product>,
    title: String,
    onProductClick: (Product) -> Unit,
    state: LazyListState = rememberLazyListState(),
    flingBehavior: FlingBehavior = rememberSnapFlingBehavior(lazyListState = state)
) {
    Column {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.CenterStart),
            )

            Text(
                text = stringResource(R.string.view_all),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.align(Alignment.CenterEnd),
                color = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            state = state,
            flingBehavior = flingBehavior,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(
                items = products,
                key = { it.id }
            ) { item ->
                val isVisible = remember { mutableStateOf(false) }

                LaunchedEffect(true) {
                    isVisible.value = true
                }

                AnimatedVisibility(
                    visible = isVisible.value,
                    enter = fadeIn() + expandVertically()
                ) {
                    ProductItem(product = item, onProductClick = onProductClick)
                }
            }
        }
    }
}