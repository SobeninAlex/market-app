package com.example.domain.entity

data class Product(
    val categoryId: Int,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val title: String
) {
    val priceString: String
        get() = "$$price"
}