package com.example.cart.presentation

import com.example.utils.domain.Cart

data class CartScreenUiState(
    val loading: Boolean,
    val refresh: Boolean,
    val carts: List<Cart>
) {

    companion object {
        val DEFAULT = CartScreenUiState(
            loading = false,
            refresh = false,
            carts = emptyList()
        )
    }

}