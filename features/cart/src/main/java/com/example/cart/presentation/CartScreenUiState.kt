package com.example.cart.presentation

import androidx.compose.runtime.Immutable
import com.example.utils.domain.Cart
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class CartScreenUiState(
    val loading: Boolean,
    val refresh: Boolean,
    val carts: List<Cart>
) {

    companion object {
        val DEFAULT = CartScreenUiState(
            loading = false,
            refresh = false,
            carts = persistentListOf()
        )

        val FAKE = CartScreenUiState(
            loading = false,
            refresh = false,
            carts = persistentListOf(
                Cart(
                    id = 681,
                    productId = 9,
                    price = 150.0,
                    imageUrl = "https://robbreport.com/wp-content/uploads/2022/07/tomcruiseaviators.jpg?w=1000",
                    quantity = 1,
                    productName = "Ray-Ban Aviator Sunglasses"
                ),
                Cart(
                    id = 681,
                    productId = 9,
                    price = 150.0,
                    imageUrl = "https://robbreport.com/wp-content/uploads/2022/07/tomcruiseaviators.jpg?w=1000",
                    quantity = 1,
                    productName = "Ray-Ban Aviator Sunglasses"
                )
            )
        )
    }

}