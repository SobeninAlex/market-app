package com.example.market_app.ui.screens.product_details

import com.example.domain.entity.Product

sealed interface ProductDetailsEvent {

    data class AddProductToCart(val product: Product) : ProductDetailsEvent

}