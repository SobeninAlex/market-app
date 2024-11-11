package com.example.domain.repository

import com.example.domain.entity.Cart
import com.example.domain.entity.CartList
import com.example.domain.entity.request.AddCartRequest
import com.example.domain.network.ResultWrapper

interface CartRepository {

    suspend fun addProductToCart(request: AddCartRequest) : ResultWrapper<CartList>

}