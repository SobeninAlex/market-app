package com.example.domain.entity

data class Product(
    val id: Long,
    val title: String,
    val price: Double,
    val category: String,
    val description: String,
    val imageUrl: String
) {
    val priceString: String
        get() = "$$price"
}