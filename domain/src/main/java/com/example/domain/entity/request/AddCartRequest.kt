package com.example.domain.entity.request

data class AddCartRequest(
    val productId: Int,
    val productName: String,
    val price: Double,
    val quantity: Int,
    val userId: Int,
)
