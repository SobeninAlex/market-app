package com.example.cart.presentation.cart

import androidx.compose.runtime.Immutable
import com.example.domain.Cart

@Immutable
data class CartUiState(
    val loading: Boolean,
    val refresh: Boolean,
    val carts: List<Cart>
) {

    companion object {
        val DEFAULT = CartUiState(
            loading = false,
            refresh = false,
            carts = emptyList()
        )

        val FAKE = CartUiState(
            loading = false,
            refresh = false,
            carts = listOf(
                Cart.FAKE,
                Cart.FAKE,
            )
        )
    }

}