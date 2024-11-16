package com.example.utils.domain.request

data class AddCartRequest(
    val productId: Int,
    val productName: String,
    val price: Double,
    val quantity: Int,
    val userId: Int,
)
