package com.example.details.domain

import com.example.domain.Cart
import com.example.utils.helper.NetworkResultWrapper

interface DetailRepository {

    suspend fun addProductToCart(
        productId: Int,
        quantity: Int,
    ): NetworkResultWrapper<List<Cart>>

}