package com.example.utils.domain.request

data class AddCartRequest(
    val productId: Int,
    val quantity: Int,
)
