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

        val FAKE = CartScreenUiState(
            loading = false,
            refresh = false,
            carts = listOf(
                Cart(
                    id = 681,
                    productId = 9,
                    userId = 1,
                    name = "Ray-Ban Aviator Sunglasses",
                    price = 150.0,
                    imageUrl = "https://robbreport.com/wp-content/uploads/2022/07/tomcruiseaviators.jpg?w=1000",
                    quantity = 1,
                    productName = "Ray-Ban Aviator Sunglasses"
                ),
                Cart(
                    id = 681,
                    productId = 9,
                    userId = 1,
                    name = "Ray-Ban Aviator Sunglasses",
                    price = 150.0,
                    imageUrl = "https://robbreport.com/wp-content/uploads/2022/07/tomcruiseaviators.jpg?w=1000",
                    quantity = 1,
                    productName = "Ray-Ban Aviator Sunglasses"
                )
            )
        )
    }

}