package com.example.details

import com.example.domain.Product

sealed interface ProductDetailsEvent {

    data class AddProductToCart(val product: Product) : ProductDetailsEvent

}