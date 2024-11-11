package com.example.data.dto

import com.example.domain.entity.Cart
import kotlinx.serialization.Serializable

@Serializable
data class CartDto(
    val id: Int,
    val productId: Int,
    val userId: Int,
    val name: String,
    val price: Double,
    val imageUrl: String?,
    val quantity: Int,
    val productName: String
) {

    fun toCart() = Cart(
        id = id,
        productId = productId,
        userId = userId,
        name = name,
        price = price,
        imageUrl = imageUrl,
        quantity = quantity,
        productName = productName
    )

}
