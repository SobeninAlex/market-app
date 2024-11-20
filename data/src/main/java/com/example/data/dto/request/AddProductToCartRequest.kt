package com.example.data.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class AddProductToCartRequest(
    val productId: Int,
    val quantity: Int,
)
