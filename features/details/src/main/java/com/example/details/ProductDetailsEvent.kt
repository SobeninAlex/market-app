package com.example.details

import com.example.utils.domain.Product

sealed interface ProductDetailsEvent {

    data class AddProductToCart(val product: Product) : ProductDetailsEvent

}