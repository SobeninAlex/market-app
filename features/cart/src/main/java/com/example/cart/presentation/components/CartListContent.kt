package com.example.cart.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.utils.domain.Cart

@Composable
fun CartListContent(
    carts: List<Cart>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {

    }
}

@Composable
fun CartListContentShimmer() {

}