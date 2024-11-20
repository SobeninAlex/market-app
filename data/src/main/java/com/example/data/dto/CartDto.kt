package com.example.data.dto

import com.example.domain.Cart
import kotlinx.serialization.Serializable

@Serializable
data class CartDto(
    val id: Int,
    val productId: Int,
    val price: Double,
    val imageUrl: String? = null,
    val quantity: Int,
    val productName: String
) {

    fun toCart() = Cart(
        id = id,
        productId = productId,
        price = price,
        imageUrl = imageUrl,
        quantity = quantity,
        productName = productName
    )

}
