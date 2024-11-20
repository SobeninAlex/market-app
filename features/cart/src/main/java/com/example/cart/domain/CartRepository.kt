package com.example.cart.domain

import com.example.domain.Cart
import com.example.domain.Summary
import com.example.utils.helper.NetworkResultWrapper
import kotlinx.coroutines.flow.Flow

interface CartRepository {

    val cart: Flow<NetworkResultWrapper<List<Cart>>>

    suspend fun updateQuantity(cart: Cart): NetworkResultWrapper<List<Cart>>

    suspend fun removeItem(cartId: Int, userId: Int): NetworkResultWrapper<List<Cart>>

    suspend fun getCartSummary(userId: Int): NetworkResultWrapper<Summary>

}