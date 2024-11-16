package com.example.utils.domain

data class Cart(
    val id: Int,
    val productId: Int,
    val userId: Int,
    val name: String,
    val price: Double,
    val imageUrl: String?,
    val quantity: Int,
    val productName: String
)
